package br.com.fiap.lunchtech.lunchtech.controllers;

import br.com.fiap.lunchtech.lunchtech.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/login")
@Tag(name = "Login", description = "Endpoint para validação de login de usuário")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(
        summary = "Login de usuário",
        description = "Faz a validação de usuário e senha para login. Deve-se informar o login de usuario e senha. Exemplo: http://localhost:8080/login?usuario=blbernat&password=123",
        responses = { @ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Unauthorized", responseCode = "401")})
    @PostMapping
    public ResponseEntity<Void> userLogin(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
        logger.info("[Login] - Iniciando o processo de login do usuário {}.", usuario);
        loginService.validateLogin(usuario, password);
        return ResponseEntity.ok().build();
    }
}
