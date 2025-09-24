package br.com.fiap.locatech.locatech.repositories;

import br.com.fiap.locatech.locatech.entities.Aluguel;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AluguelRepositoryImp implements AluguelRepository{
    private JdbcClient jdbcClient;

    public AluguelRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Optional<Aluguel> findById(Long id) {
        return this.jdbcClient.sql("""
                        SELECT a.id,
                            a.pessoa_id,
                            a.veiculo_id,
                            a.data_inicio,
                            a.data_fim,
                            a.valor_total,
                            p.nome as pessoa_nome,
                            p.cpf as pessoa_cpf,
                            v.modelo as veiculo_modelo
                        FROM aluguel a
                            inner join pessoa p on a.pessoa_id = p.id
                            inner join veiculos v on a.veiculo_id = v.id
                        where a.id = :id
                        """)
                .param("id", id)
                .query(Aluguel.class)
                .optional();
    }

    @Override
    public List<Aluguel> findAll(int size, int offset) {
        return this.jdbcClient.sql("""
                        SELECT a.id,
                            a.pessoa_id,
                            a.veiculo_id,
                            a.data_inicio,
                            a.data_fim,
                            a.valor_total,
                            p.nome as pessoa_nome,
                            p.cpf as pessoa_cpf,
                            v.modelo as veiculo_modelo
                        FROM aluguel a
                            inner join pessoa p on a.pessoa_id = p.id
                            inner join veiculos v on a.veiculo_id = v.id
                        LIMIT :size OFFSET :offset
                        """)
                .param("size", size)
                .param("offset", offset)
                .query(Aluguel.class)
                .list();
    }

    @Override
    public Integer save(Aluguel aluguel) {
        return this.jdbcClient.sql("INSERT INTO aluguel (" +
                        "pessoa_Id," +
                        " veiculo_Id," +
                        " data_Inicio," +
                        " data_Fim," +
                        " valor_Total)" +
                        " values(" +
                        ":pessoaId," +
                        " :veiculoId," +
                        " :dataInicio," +
                        " :dataFim," +
                        " :valorTotal)")
                .param("pessoaId",aluguel.getPessoaId())
                .param("veiculoId",aluguel.getVeiculoId())
                .param("dataInicio", aluguel.getDataInicio())
                .param("dataFim", aluguel.getDataFim())
                .param("valorTotal", aluguel.getValorTotal())
                .update();
    }

    @Override
    public Integer update(Aluguel aluguel, Long id) {
        return this.jdbcClient.sql("""
                        UPDATE aluguel
                        set pessoa_Id = :pessoaId,
                        veiculo_Id = :veiculoId,
                        data_Inicio = :dataInicio,
                        data_Fim = :dataFim,
                        valor_Total = :valorTotal
                        where id = :id
                        """)
                .param("id", id)
                .param("pessoaId",aluguel.getPessoaId())
                .param("veiculoId",aluguel.getVeiculoId())
                .param("dataInicio", aluguel.getDataInicio())
                .param("dataFim", aluguel.getDataFim())
                .param("valorTotal", aluguel.getValorTotal())
                .update();
    }

    @Override
    public Integer delete(Long id) {
        return this.jdbcClient.sql("DELETE FROM aluguel where id = :id")
                .param("id", id)
                .update();
    }
    
}
