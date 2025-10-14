package br.com.fiap.lunchtech.lunchtech.dtos;

import java.util.List;

public record MethodArgumentDTO(List<String> erros, int status) {
}
