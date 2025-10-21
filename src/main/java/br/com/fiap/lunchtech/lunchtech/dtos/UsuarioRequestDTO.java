package br.com.fiap.lunchtech.lunchtech.dtos;

import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(
        @Schema(description = "Dados do usuário")
        @NotNull(message = "O nome do usuário não pode ser nulo") String nome,
        @NotNull(message = "O email do usuário não pode ser nulo") String email,
        @NotNull(message = "O login não pode ser nulo") String login,
        @NotNull(message = "A senha não pode ser nula") String senha,
        EnderecoDTO enderecoDTO,
        @NotNull(message = "O tipo de usuário não pode ser nulo") TipoUsuario tipoUsuario
        ) {
}
