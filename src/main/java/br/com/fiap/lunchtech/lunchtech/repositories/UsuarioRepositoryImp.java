package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioCreateDTO;
import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioUpdateDTO;
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
    public List<Usuario> findByName(String nomeUsuario) {
        return this.jdbcClient.sql("SELECT * FROM USUARIO where upper(nome) = upper(:nomeUsuario)")//trocar para like
                .param("nomeUsuario", nomeUsuario)
                .query(new UsuarioRowMapper())
                .list();
    }

    @Override
    public List<Usuario> findAll(int size, int offset) {
        return this.jdbcClient.sql("SELECT * FROM USUARIO LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(new UsuarioRowMapper())
                .list();
    }

    @Override
    public Integer save(UsuarioCreateDTO usuario) {
        return this.jdbcClient.sql("INSERT INTO USUARIO " +
                        " (nome, login, senha, email, data_atualizacao, rua, cep, numero, cidade, tipo_usuario) " +
                        " values(:nome, :login, :senha, :email, :data_atualizacao, :rua, :cep, :numero, :cidade, :tipo_usuario)")
                .param("nome",usuario.nome())
                .param("login",usuario.login())
                .param("senha", usuario.senha())
                .param(EMAIL, usuario.email())
                .param(DATA_ATUALIZACAO, LocalDate.now())
                .param("rua", usuario.endereco().rua())
                .param("cep", usuario.endereco().cep())
                .param("numero", usuario.endereco().numero())
                .param("cidade", usuario.endereco().cidade())
                .param("tipo_usuario", usuario.tipoUsuario().name())
                .update();
    }

    @Override
    public Integer update(UsuarioUpdateDTO usuario, String email) {
        return this.jdbcClient.sql("UPDATE USUARIO set nome = :nome, login = :login, email = :email, " +
                        "data_atualizacao = :data_atualizacao, rua = :rua, cep = :cep, numero = :numero, cidade = :cidade, tipo_usuario = :tipo_usuario" +
                        " where email = :emailAtual")
                .param("nome",usuario.nome())
                .param("login",usuario.login())
                .param(EMAIL, usuario.email())
                .param("emailAtual", email)
                .param(DATA_ATUALIZACAO, LocalDate.now())
                .param("rua", usuario.endereco().rua())
                .param("cep", usuario.endereco().cep())
                .param("numero", usuario.endereco().numero())
                .param("cidade", usuario.endereco().cidade())
                .param("tipo_usuario", usuario.tipoUsuario().name())
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

    @Override
    public Optional<String> findUsername(String username) {
        return this.jdbcClient.sql("SELECT max(login) FROM USUARIO where login = :username")
                .param("username", username)
                .query(String.class)
                .optional();
    }

    @Override
    public Optional<String> getPassword(String username) {
        return this.jdbcClient.sql("SELECT max(senha) FROM USUARIO where login = :username")
                .param("username", username)
                .query(String.class)
                .optional();
    }
}
