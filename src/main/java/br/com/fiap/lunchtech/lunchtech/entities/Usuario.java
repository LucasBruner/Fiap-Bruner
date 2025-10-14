package br.com.fiap.lunchtech.lunchtech.entities;

import br.com.fiap.lunchtech.lunchtech.dtos.EnderecoDTO;
import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;

import java.time.LocalDate;

public class Usuario {
    private String nome;
    private String email; //Unique key
    private String login;
    private String senha;
    private LocalDate dataAtualizacao;
    private EnderecoDTO endereco;
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, String email, String login, String senha, EnderecoDTO endereco, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.senha = senha;
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
