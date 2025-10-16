package br.com.fiap.lunchtech.lunchtech.entities;

import br.com.fiap.lunchtech.lunchtech.dtos.EnderecoDTO;
import br.com.fiap.lunchtech.lunchtech.dtos.UsuarioRequestDTO;
import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private String nome;
    private String email; //Unique key
    private String login;
    private String senha;
    private LocalDate dataAtualizacao;
    private EnderecoDTO endereco;
    private TipoUsuario tipoUsuario;

    public Usuario(UsuarioRequestDTO usuarioRequestDTO, EnderecoDTO endereco, TipoUsuario tipoUsuario) {
        this.nome = usuarioRequestDTO.nome();
        this.email = usuarioRequestDTO.email();
        this.login = usuarioRequestDTO.login();
        this.senha = usuarioRequestDTO.senha();
        this.endereco = endereco;
        this.tipoUsuario = tipoUsuario;
        this.dataAtualizacao = LocalDate.now();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}
