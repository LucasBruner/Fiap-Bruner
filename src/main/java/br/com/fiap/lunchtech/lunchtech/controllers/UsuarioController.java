package br.com.fiap.lunchtech.lunchtech.controllers;

import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioResponseDTO;
import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
        summary = "Busca todos os usuários",
        description = "Busca todos os usuários. Deve-se informar a página e o tamanho da paginação para o retorno dos resultados. Retorna uma lista de JSON. Exemplo URL: http://localhost:8080/usuarios?page=1&size=10",
        responses = { @ApiResponse(description = "Ok", responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> findAllUsuarios(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        logger.info("GET -> /usuarios");
        List<Usuario> usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(convertUserToDTO(usuarios));
    }

    @Operation(
        summary = "Busca usuários por nome",
        description = "Busca usuários por nome. Deve-se informar na URL um nome para o retorno dos resultados. Retorna uma lista de JSON. Exemplo URL: http://localhost:8080/usuarios/brenda",
        responses = { @ApiResponse(description = "Ok", responseCode = "200")})
    @GetMapping("/{name}")
    public ResponseEntity<List<UsuarioResponseDTO>> findUsuarioByName(@PathVariable("name") String name) {
        logger.info("GET -> /usuarios/name");
        List<Usuario> usuario = this.usuarioService.findUsuarioByName(name);
        return ResponseEntity.ok(convertUserToDTO(usuario));
    }

    @Operation(
        summary = "Criação de novo usuário",
        description = "Criação de novo usuário, onde são feitas as validações das regras dos campos e salva o novo usuário. Deve-se informar um JSON com as informações de usuário.",
        responses = { @ApiResponse(description = "Created", responseCode = "201"), @ApiResponse(description = "Bad request", responseCode = "400")})
    @PostMapping
    public ResponseEntity<Void> saveUsuario(@Valid @RequestBody Usuario usuario) {
        logger.info("POST -> /usuarios");
        this.usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).build();
    }

    @Operation(
        summary = "Alteração de informações do usuário",
        description = "Alteração de informações do usuário, onde são feitas as validações das regras dos campos e atualiza as informações do usuário. Deve-se informar um JSON com as informações de usuário e na URL informar o email do usuário que será alterado. A data de alteração será atualizada automaticamente com a data atual do sistema.",
        responses = { @ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Bad request", responseCode = "400")})
    @PutMapping("/{email}")
    public ResponseEntity<Void> updateUsuario(@Valid @RequestBody Usuario usuario,
                                              @PathVariable("email") String email) {
        logger.info("PUT -> /usuarios");
        this.usuarioService.updateUsuario(usuario, email);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Exclusão de usuário",
        description = "Exclusão de usuário. Deve-se informar o email do usuário que será excluído. Exemplo: http://localhost:8080/usuarios/delete?email=joao@fiap.com",
        responses = { @ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Not found", responseCode = "404")})
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("email") String email) {
        logger.info("DELETE -> /usuarios");
        this.usuarioService.deleteUsuario(email);
        return ResponseEntity.ok().build();
    }

    @Operation(
        summary = "Alteração da senha do usuário",
        description = "Alteração da senha do usuário. Deve-se informar o email do usuário que será alterado e a nova senha. A senha não pode ser vazia ou possuir apenas espaços. A data de alteração será atualizada automaticamente com a data atual do sistema. Exemplo: http://localhost:8080/usuarios/change-password/joao@fiap.com?password=123",
        responses = { @ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Not found", responseCode = "404")})
    @PutMapping("/change-password/{email}")
    public ResponseEntity<Void> changePassword(@PathVariable("email") String email,
                                               @RequestParam("password") String password) {
        logger.info("PUT -> /change-password");
        usuarioService.changePassword(email, password);
        return ResponseEntity.ok().build();
    }

    private List<UsuarioResponseDTO> convertUserToDTO(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuarioService::getResponseUserBody)
                .collect(Collectors.toList());
    }
}
