package br.com.fiap.lunchtech.lunchtech.repositories;

import br.com.fiap.lunchtech.lunchtech.entities.Veiculo;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository {
    Optional<Veiculo> findById(Long id);
    List<Veiculo> findAll(int size, int offset);
    Integer save(Veiculo veiculo);
    Integer update (Veiculo veiculo, Long id);
    Integer delete(Long id);
}
