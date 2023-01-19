package br.com.julgamento.repository;

import br.com.julgamento.model.entity.SessaoJulgamento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessaoJulgamentoRepository extends MongoRepository<SessaoJulgamento, String> {
}
