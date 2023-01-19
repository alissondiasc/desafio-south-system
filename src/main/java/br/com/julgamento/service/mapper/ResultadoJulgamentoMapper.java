package br.com.julgamento.service.mapper;

import br.com.julgamento.model.entity.ResultadoJulgamento;
import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.response.ResultadoJulgamentoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.julgamento.model.entity.enums.ResultadoVotacao.getValue;

@AllArgsConstructor
@Component
public class ResultadoJulgamentoMapper implements AbstractMapper<ResultadoJulgamento, ResultadoJulgamentoResponse> {

  @Override
  public ResultadoJulgamentoResponse entidadeParaDTO(ResultadoJulgamento entidade) {
    return ResultadoJulgamentoResponse.builder()
        .id(entidade.getId())
        .idJulgamento(entidade.getSessaoJulgamento().getId())
        .resultadoVotacao(entidade.getResultadoVotacao().getValor())
        .build();
  }

  @Override
  public ResultadoJulgamento dtoParaEntidade(ResultadoJulgamentoResponse resultadoJulgamentoResponse) {
    return ResultadoJulgamento.builder()
        .sessaoJulgamento(SessaoJulgamento.builder().id(resultadoJulgamentoResponse.getResultadoVotacao()).build())
        .resultadoVotacao(getValue(resultadoJulgamentoResponse.getResultadoVotacao()))
        .build();
  }
}
