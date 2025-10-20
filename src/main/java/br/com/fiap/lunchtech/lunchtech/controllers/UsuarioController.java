package br.com.fiap.lunchtech.lunchtech.controllers;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.services.UsuarioService;
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

    @GetMapping
    public ResponseEntity<List<Usuario>> findAllUsuarios(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        logger.info("Foi acessado o endpoint de usuarios /usuarios - findAllUsuarios");
        List<Usuario> usuarios = this.usuarioService.findAllUsuarios(page, size);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Usuario>> findUsuarioByName(@PathVariable("name") String name) {
        logger.info("Foi realizado uma busca por nome");
        List<Usuario> usuario = this.usuarioService.findUsuarioByName(name);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Void> saveUsuario(@RequestBody Usuario usuario) {
        logger.info("Post -> /usuarios");
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

    @PutMapping("/changePassword/{email}")
    public ResponseEntity<Void> changePassword(@PathVariable("email") String email,
                                               @RequestParam("password") String password) {
        logger.info("PUT -> /changePassword");
        usuarioService.changePassword(email, password);
        return ResponseEntity.ok().build();
    }
}
