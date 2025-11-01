package br.com.fiap.lunchtech.lunchtech.services;

import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioCreateDTO;
import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioResponseDTO;
import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioUpdateDTO;
import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.repositories.UsuarioRepository;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.CreateUserException;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findUsers(int page, int size, String name) {
        int offset = (page -1) * size;
        return this.usuarioRepository.findUsers(size, offset, name);
    }

    public void saveUsuario(UsuarioCreateDTO usuario) {
        Optional<String> validateEmail = this.usuarioRepository.findEmail(usuario.email());
        if (validateEmail.isPresent()) {
            throw new CreateUserException("Já existe um usuário cadastrado com este email: " + usuario.email());
        }

        Integer save = this.usuarioRepository.save(usuario);
        if (save != 1) {
            throw new ResourceNotFoundException("Houve um erro ao criar usuário!");
        }
    }

    public void updateUsuario(UsuarioUpdateDTO usuario, String email) {
        Optional<String> validateEmail = this.usuarioRepository.findEmail(usuario.email());
        Optional<String> validateEmailPath = this.usuarioRepository.findEmail(email);

        if (validateEmailPath.isEmpty()) {
            throw new ResourceNotFoundException("Cadastro com email " + email + " não encontrado!");
        }
        if (validateEmail.isPresent() && !validateEmail.stream().findFirst().get().equals(email)) {
            throw new CreateUserException("Já existe um usuário cadastrado com este email: " + usuario.email());
        }

        Integer update = this.usuarioRepository.update(usuario, email);
        if (update == 0) {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
    }

    public void deleteUsuario(String email) {
        Integer delete = this.usuarioRepository.delete(email);
        if (delete == 0) {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
    }

    public void changePassword(String email, String password) {
        if (password.isBlank()) {
            throw new ResourceNotFoundException("A nova senha não pode ser vazia!");
        }

        Integer update = this.usuarioRepository.updatePassword(email, password);
        if (update == 0) {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
    }

    public UsuarioResponseDTO getResponseUserBody(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getDataAtualizacao(),
                usuario.getEndereco(),
                usuario.getTipoUsuario());
    }

    private void handleDataIntegrityViolation(DataIntegrityViolationException e, String email) {
        Throwable rootCause = e.getMostSpecificCause();
        if (rootCause instanceof SQLIntegrityConstraintViolationException) {
            throw new CreateUserException("Já existe um usuário cadastrado com esse email: " + email);
        }
        throw e;
    }
}
