package br.com.fiap.lunchtech.lunchtech.entities;

import br.com.fiap.lunchtech.lunchtech.dtos.EnderecoDTO;
import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    private String nome;
    private String email; //Unique key
    private String login;
    private String senha;
    private LocalDate dataAtualizacao;
    private EnderecoDTO endereco;
    private TipoUsuario tipoUsuario;
}
