package br.com.fiap.lunchtech.lunchtech.services;

import br.com.fiap.lunchtech.lunchtech.controllers.LoginController;
import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.repositories.UsuarioRepository;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.InvalidLoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void validateLogin(String usuario, String password) {
        String validateUser = getUser(usuario).orElse("");
        String validatePassword = getPassword(usuario).orElse("");
        if (!usuario.equals(validateUser) || !password.equals(validatePassword)) {
            logger.info("[Login] - Login mal sucedido.");
            throw new InvalidLoginException("Usuário ou senha incorretos!");
        }
        logger.info("[Login] - Login realizado com sucesso.");
    }

    private Optional<String> getUser(String usuario) {
        return this.usuarioRepository.findUsername(usuario);
    }

    private Optional<String> getPassword(String username) {
        return this.usuarioRepository.getPassword(username);
    }
}
