package br.com.fiap.lunchtech.lunchtech.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UsuarioRequestDTO(@Schema(description = "Email do usuário.")
                                @NotNull(message = "O email do usuário não pode ser nulo!") String email) {
}
