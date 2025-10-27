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
@RequestMapping("/v2/login")
@Tag(name = "Login", description = "Endpoint para validação de login de usuário")
public class LoginControllerV2 {

    private static final Logger logger = LoggerFactory.getLogger(LoginControllerV2.class);
    private final LoginService loginService;

    public LoginControllerV2(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(
        summary = "Login de usuário - v2",
        description = "Método criado para demonstração do versionamento da API. Faz a validação de usuário e senha para login. Deve-se informar o login de usuario e senha. Exemplo: http://localhost:8080/login?usuario=blbernat&password=123",
        responses = { @ApiResponse(description = "Ok", responseCode = "200"), @ApiResponse(description = "Unauthorized", responseCode = "401")})
    @PostMapping
    public ResponseEntity<Void> userLoginV2(@RequestParam("usuario") String usuario, @RequestParam("password") String password) {
        logger.info("[Login - v2] - Iniciando o processo de login (versão 2) do usuário {}.", usuario);
        loginService.validateLogin(usuario, password);
        return ResponseEntity.ok().build();
    }
}
