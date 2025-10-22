package br.com.fiap.lunchtech.lunchtech.dtos;

import br.com.fiap.lunchtech.lunchtech.enums.TipoUsuario;

import java.time.LocalDate;

public record UsuarioResponseDTO (String nome,
                                  String email,
                                  String login,
                                  LocalDate dataAtualizacao,
                                  EnderecoDTO endereco,
                                  TipoUsuario tipoUsuario){
}
