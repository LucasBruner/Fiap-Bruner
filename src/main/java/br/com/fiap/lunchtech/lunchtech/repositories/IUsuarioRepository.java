package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findByName(String nomeUsuario);
    List<Usuario> findAll(int size, int offset);
    Integer save(Usuario usuario);
    Integer update (Usuario usuario, String email);
    Integer delete(String email);
    Integer updatePassword(String email, String newPassword);
}
