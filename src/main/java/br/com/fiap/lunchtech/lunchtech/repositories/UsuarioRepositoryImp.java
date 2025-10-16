package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositoryImp implements UsuarioRepository {
    private final JdbcClient jdbcClient;
    private static final String EMAIL = "email";
    private static final String DATA_ATUALIZACAO = "data_atualizacao";


    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findByName(String nomeUsuario) {
        return this.jdbcClient.sql("SELECT * FROM USUARIO where nome = :nomeUsuario")
                .param("nomeUsuario", nomeUsuario)
                .query(Usuario.class)
                .optional();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient.sql("SELECT * FROM USUARIO LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient.sql("INSERT INTO USUARIO " +
                        " (nome, login, senha, email, data_atualizacao, rua, cep, numero, cidade, tipo_usuario) " +
                        " values(:nome, :login, :senha, :email, :data_atualizacao, :rua, :cep, :numero, :cidade, :tipo_usuario)")
                .param("nome",usuario.getNome())
                .param("login",usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param(EMAIL, usuario.getEmail())
                .param(DATA_ATUALIZACAO, LocalDate.now())
                .param("rua", usuario.getEndereco().rua())
                .param("cep", usuario.getEndereco().cep())
                .param("numero", usuario.getEndereco().numero())
                .param("cidade", usuario.getEndereco().cidade())
                .param("tipo_usuario", usuario.getTipoUsuario())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, String email) {
        return this.jdbcClient.sql("UPDATE USUARIO set nome = :nome, login = :login, senha = :senha, email = :email, " +
                        "data_atualizacao = :data_atualizacao, rua = :rua, cep = :cep, numero = :numero, cidade = :cidade, tipo_usuario = :tipo_usuario" +
                        " where email = :email")
                .param("nome",usuario.getNome())
                .param("login",usuario.getLogin())
                .param(EMAIL, usuario.getEmail())
                .param(DATA_ATUALIZACAO, LocalDate.now())
                .param("rua", usuario.getEndereco().rua())
                .param("cep", usuario.getEndereco().cep())
                .param("numero", usuario.getEndereco().numero())
                .param("cidade", usuario.getEndereco().cidade())
                .param("tipo_usuario", usuario.getTipoUsuario())
                .update();
    }

    @Override
    public Integer delete(String email) {
        return this.jdbcClient.sql("DELETE FROM USUARIO where email = :email")
                .param(EMAIL, email)
                .update();
    }

    @Override
    public Integer updatePassword(String email, String newPassword) {
        return this.jdbcClient.sql("UPDATE USUARIO set senha = :senha where email = :email")
                .param(EMAIL, email)
                .param(DATA_ATUALIZACAO, LocalDate.now())
                .param("senha", newPassword)
                .update();
    }
}
