package br.com.julgamento.service;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.enums.Indicador;
import br.com.julgamento.model.request.SessaoJulgamentoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SessaoJulgamentoService {

  void create(SessaoJulgamentoRequest sessaoJulgamentoRequest);

  SessaoJulgamento getTrialSession(String idSessaoJulgamento);

  void atualizarIndicador(SessaoJulgamento sessaoJulgamento, Indicador n);

  Page<SessaoJulgamentoRequest> getTrialSessionPageable(Pageable pageable);
}
