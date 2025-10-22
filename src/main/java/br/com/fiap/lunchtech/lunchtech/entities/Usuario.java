package br.com.fiap.lunchtech.lunchtech.entities;

import br.com.fiap.lunchtech.lunchtech.dtos.EnderecoDTO;
import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "É obrigatório informar o nome!")
    private String nome;

    @Email(message = "O formato do email é inválido!")
    @NotBlank(message = "É obrigatório informar o email!")
    private String email; //Unique key

    @NotBlank(message = "É obrigatório informar o login!")
    private String login;

    @NotBlank(message = "É obrigatório informar a senha!")
    private String senha;

    @NotNull(message = "É obrigatório informar o tipo de usuário")
    private TipoUsuario tipoUsuario;

    private LocalDate dataAtualizacao;
    private EnderecoDTO endereco;
}
