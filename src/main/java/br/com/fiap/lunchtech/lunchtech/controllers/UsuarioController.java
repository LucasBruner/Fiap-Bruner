package br.com.fiap.lunchtech.lunchtech.controllers;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            description = "Busca todos os usuários",
            summary = "Busca todos os usuários",
            responses = { @ApiResponse(description = "Ok", responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        logger.info("GET -> /usuarios");
        List<Usuario> usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @Operation(
            description = "Busca os usuários por nome",
            summary = "Busca de usuários por nome",
            responses = { @ApiResponse(description = "Ok", responseCode = "200")})
    @GetMapping("/{name}")
    public ResponseEntity<List<Usuario>> findUsuarioByName(@PathVariable("name") String name) {
        logger.info("GET -> /usuarios/name");
        List<Usuario> usuario = this.usuarioService.findUsuarioByName(name);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(@RequestBody Usuario usuario) {
        logger.info("POST -> /usuarios");
        this.usuarioService.saveUsuario(usuario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{email}")
    public ResponseEntity<Void> updateUsuario(@RequestBody Usuario usuario,
                                              @PathVariable("email") String email) {
        logger.info("PUT -> /usuarios");
        this.usuarioService.updateUsuario(usuario, email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUsuario(@RequestParam("email") String email) {
        logger.info("DELETE -> /usuarios");
        this.usuarioService.deleteUsuario(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/change-password/{email}")
    public ResponseEntity<Void> changePassword(@PathVariable("email") String email,
                                               @RequestParam("password") String password) {
        logger.info("PUT -> /change-password");
        usuarioService.changePassword(email, password);
        return ResponseEntity.ok().build();
    }
}
