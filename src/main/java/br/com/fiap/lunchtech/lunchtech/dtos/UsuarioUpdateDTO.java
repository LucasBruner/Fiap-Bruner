package br.com.fiap.lunchtech.lunchtech.dtos;

import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UsuarioUpdateDTO (@NotBlank(message = "É obrigatório informar o nome!")
                                String nome,
                                @Email(message = "O formato do email é inválido!")
                                @NotBlank(message = "É obrigatório informar o email!")
                                String email,
                                @NotBlank(message = "É obrigatório informar o login!")
                                String login,
                                @NotNull(message = "É obrigatório informar o tipo de usuário")
                                TipoUsuario tipoUsuario,
                                LocalDate dataAtualizacao,
                                EnderecoDTO endereco) {
}
