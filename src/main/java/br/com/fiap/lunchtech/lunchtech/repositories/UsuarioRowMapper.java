package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.dtos.EnderecoDTO;
import br.com.fiap.lunchtech.lunchtech.entities.Usuario;
import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;

public class UsuarioRowMapper implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        EnderecoDTO enderecoDTO = new EnderecoDTO(rs.getString("RUA"),
                rs.getInt("NUMERO"),
                rs.getString("CIDADE"),
                rs.getInt("CEP"));
        Usuario usuario = new Usuario();
        usuario.setNome(rs.getString("NOME"));
        usuario.setEmail(rs.getString("EMAIL"));
        usuario.setLogin(rs.getString("LOGIN"));
        usuario.setSenha(rs.getString("SENHA"));
        usuario.setEndereco(enderecoDTO);

        Timestamp dataAtualizacaoSql = rs.getTimestamp("DATA_ATUALIZACAO");
        if (dataAtualizacaoSql != null) {
            usuario.setDataAtualizacao(dataAtualizacaoSql.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate());
        } else {
            usuario.setDataAtualizacao(null);
        }
        String tipoUsuario = rs.getString("TIPO_USUARIO");
        usuario.setTipoUsuario(TipoUsuario.valueOf(tipoUsuario));
        return usuario;
    }
}
