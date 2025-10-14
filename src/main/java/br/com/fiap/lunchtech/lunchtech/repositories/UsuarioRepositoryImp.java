package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UsuarioRepositoryImp implements IUsuarioRepository{
    private final JdbcClient jdbcClient;
    private static final String EMAIL = "email";
    private static final String DATA_ATUALIZACAO = "dataAtualizacao";


    public UsuarioRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Usuario> findByName(String nomeUsuario) {
        return this.jdbcClient.sql("SELECT * FROM USUARIO where usuario = :usuario")
                .param("usuario", nomeUsuario)
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
        return this.jdbcClient.sql("INSERT INTO USUARIO" +
                        " (nome, login, senha, email, dataAtualizacao, rua, cep, numero, cidade, tipoUsuario)" +
                        " values(:nome, :login, :senha, :email, :dataAtualizacao, :rua, :cep, :numero, :cidade, :tipoUsuario)")
                .param("nome",usuario.getNome())
                .param("login",usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param(EMAIL, usuario.getEmail())
                .param(DATA_ATUALIZACAO, usuario.getDataAtualizacao())
                .param("rua", usuario.getEndereco().rua())
                .param("cep", usuario.getEndereco().cep())
                .param("numero", usuario.getEndereco().numero())
                .param("cidade", usuario.getEndereco().cidade())
                .param("tipoUsuario", usuario.getTipoUsuario())
                .update();
    }

    @Override
    public Integer update(Usuario usuario, String email) {
        return this.jdbcClient.sql("UPDATE USUARIO set nome = :nome, login = :login, email = :email, " +
                        "dataAtualizacao = :dataAtualizacao, rua = :rua, cep = :cep, numero = :numero, cidade = :cidade, tipoUsuario = :tipoUsuario" +
                        " where email = :email")
                .param("nome",usuario.getNome())
                .param("login",usuario.getLogin())
                .param(EMAIL, usuario.getEmail())
                .param(DATA_ATUALIZACAO, usuario.getDataAtualizacao())
                .param("rua", usuario.getEndereco().rua())
                .param("cep", usuario.getEndereco().cep())
                .param("numero", usuario.getEndereco().numero())
                .param("cidade", usuario.getEndereco().cidade())
                .param("tipoUsuario", usuario.getTipoUsuario())
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
