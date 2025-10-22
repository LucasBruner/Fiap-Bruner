package br.com.fiap.lunchtech.lunchtech.services;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.repositories.UsuarioRepository;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.CreateUserException;
import br.com.fiap.lunchtech.lunchtech.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsuarios(int page, int size) {
        int offset = (page -1) * size;
        return this.usuarioRepository.findAll(size, offset);
    }

    public List<Usuario> findUsuarioByName(String name) {
        return this.usuarioRepository.findByName(name);
    }

    public void saveUsuario(Usuario usuario) {
        try {
            Integer save = this.usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e){
            throw new CreateUserException("Já existe um usuário cadastrado com esse email: " + usuario.getEmail());
        } catch (RuntimeException e) {
            throw new ResourceNotFoundException("Houve um erro ao criar usuário! " + e.getMessage());
        }
    }

    public void updateUsuario(Usuario usuario, String email) {
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
}
