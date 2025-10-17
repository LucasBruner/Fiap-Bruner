package br.com.fiap.lunchtech.lunchtech.controllers;

import br.com.fiap.lunchtech.lunchtech.services.LoginService;
import br.com.fiap.lunchtech.lunchtech.services.UsuarioService;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.InvalidLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Void> userLogin(@RequestParam String usuario,
                                          @RequestParam String password) {
        logger.info("[Login] - Iniciando o processo de login do usuário {}.", usuario);
        loginService.validateLogin(usuario, password);
        return ResponseEntity.ok().build();
    }
}
