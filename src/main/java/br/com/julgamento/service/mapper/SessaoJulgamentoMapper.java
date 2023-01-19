package br.com.julgamento.service.mapper;

import br.com.julgamento.model.entity.Pauta;
import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.request.SessaoJulgamentoRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Component
public class SessaoJulgamentoMapper implements AbstractMapper<SessaoJulgamento, SessaoJulgamentoRequest> {

  @Override
  public SessaoJulgamentoRequest entidadeParaDTO(SessaoJulgamento entidade) {
    return SessaoJulgamentoRequest.builder()
        .id(entidade.getId())
        .temaPauta(entidade.getPauta().getTema())
        .idPauta(entidade.getPauta().getId())
        .dataInicio(entidade.getDataInicio())
        .dataFim(entidade.getDataFim())
        .build();
  }

  @Override
  public SessaoJulgamento dtoParaEntidade(SessaoJulgamentoRequest sessaoJulgamentoRequest) {
    return SessaoJulgamento.builder()
        .pauta(Pauta.builder().id(sessaoJulgamentoRequest.getIdPauta()).build())
        .dataInicio(isNull(sessaoJulgamentoRequest.getDataInicio())
            ? LocalDateTime.now()
            : sessaoJulgamentoRequest.getDataInicio())
        .dataFim(isNull(sessaoJulgamentoRequest.getDataFim())
            ? LocalDateTime.now().plusMinutes(1)
            : sessaoJulgamentoRequest.getDataFim())
        .build();
  }
}
