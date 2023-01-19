package br.com.julgamento.repository;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.Usuario;
import br.com.julgamento.model.entity.VotoParticipacao;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VotoParticipacaoRepository extends MongoRepository<VotoParticipacao, String> {
  boolean existsByAssociadoAndSessaoJulgamento(Usuario associado, SessaoJulgamento sessaoJulgamento);

  List<VotoParticipacao> findBySessaoJulgamento(SessaoJulgamento sessaoJulgamento);
}
