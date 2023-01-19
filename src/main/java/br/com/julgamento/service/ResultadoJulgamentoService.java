package br.com.julgamento.service;

import br.com.julgamento.model.response.DetalhesResultadoJulgamentoResponse;
import br.com.julgamento.model.response.ResultadoJulgamentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ResultadoJulgamentoService {

  ResultadoJulgamentoResponse create(String idJulgamentoSessao);

  ResultadoJulgamentoResponse endTrialSessionAndCreatResulTrialSession(String idSessaoJulgamento) throws Exception;

  DetalhesResultadoJulgamentoResponse obterDetalhesResultadoJulgamento(String idResultado) throws Exception;

  Page<ResultadoJulgamentoResponse> getResultsJudgment(Pageable pageable);
}
