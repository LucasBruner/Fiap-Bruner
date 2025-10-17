package br.com.fiap.lunchtech.lunchtech.services;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

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
        Integer save = this.usuarioRepository.save(usuario);
        Assert.state(save == 1, "Erro ao salvar usuário " + usuario.getNome());
    }

    public void updateUsuario(Usuario usuario, String email) {
        Integer update = this.usuarioRepository.update(usuario, email);
        if(update == 0) {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public void deleteUsuario(String email) {
        Integer delete = this.usuarioRepository.delete(email);
        if(delete == 0) {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public void changePassword(String email, String password) {
        Integer update = this.usuarioRepository.updatePassword(email, password);
        if(update == 0) {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}
