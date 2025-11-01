package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioCreateDTO;
import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioUpdateDTO;
import br.com.fiap.lunchtech.lunchtech.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    List<Usuario> findUsers(int size, int offset, String name);
    Integer save(UsuarioCreateDTO usuario);
    Integer update (UsuarioUpdateDTO usuario, String email);
    Integer delete(String email);
    Integer updatePassword(String email, String newPassword);
    Optional<String> findUsername(String username);
    Optional<String> getPassword(String username);
    Optional<String> findEmail(String email);
}
