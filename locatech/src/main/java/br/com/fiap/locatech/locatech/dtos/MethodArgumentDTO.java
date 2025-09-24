package br.com.fiap.locatech.locatech.dtos;

import java.util.List;

public record MethodArgumentDTO(List<String> erros, int status) {
}
