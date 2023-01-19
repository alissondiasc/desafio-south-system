package br.com.julgamento.service.mapper;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.Usuario;
import br.com.julgamento.model.entity.VotoParticipacao;
import br.com.julgamento.model.request.VotoParticipacaoRequestDto;
import br.com.julgamento.model.response.VotoParticipacaoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Component
public class VotoParticipacaoMapper implements AbstractMapper<VotoParticipacao, VotoParticipacaoResponseDto> {
  @Override
  public VotoParticipacaoResponseDto entidadeParaDTO(VotoParticipacao entidade) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return VotoParticipacaoResponseDto.builder()
        .idAssociado(entidade.getAssociado().getId())
        .nomeAssociado(entidade.getAssociado().getNome())
        .votoParticipacao(entidade.getVoto())
        .dataHoraSessaoJulgamento(entidade.getSessaoJulgamento().getDataInicio().format(formatter))
        .pautaJulgada(entidade.getSessaoJulgamento().getPauta().getTema())
        .idJulgamento(entidade.getSessaoJulgamento().getId())
        .build();
  }

  @Override
  public VotoParticipacao dtoParaEntidade(VotoParticipacaoResponseDto votoParticipacaoResponseDto) {
    return VotoParticipacao.builder()
        .associado(Usuario.builder().id(votoParticipacaoResponseDto.getIdAssociado()).build())
        .sessaoJulgamento(SessaoJulgamento.builder().id(votoParticipacaoResponseDto.getIdJulgamento()).build())
        .voto(votoParticipacaoResponseDto.getVotoParticipacao())
        .build();
  }

  public VotoParticipacao dtoParaEntidade(VotoParticipacaoRequestDto votoParticipacaoRequestDto) {
    return VotoParticipacao.builder()
        .associado(Usuario.builder().id(votoParticipacaoRequestDto.getIdAssociado()).build())
        .sessaoJulgamento(SessaoJulgamento.builder().id(votoParticipacaoRequestDto.getIdJulgamento()).build())
        .voto(votoParticipacaoRequestDto.getVotoParticipacao())
        .build();
  }
}
