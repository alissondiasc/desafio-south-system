package br.com.julgamento.repository;

import br.com.julgamento.model.entity.ResultadoJulgamento;
import br.com.julgamento.model.entity.SessaoJulgamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultadoJulgamentoRepository extends MongoRepository<ResultadoJulgamento, String> {

  ResultadoJulgamento findBySessaoJulgamento(SessaoJulgamento sessaoJulgamento);
}
