package br.com.julgamento.service.mapper;

import br.com.julgamento.model.entity.Pauta;
import br.com.julgamento.model.response.PautaRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static br.com.julgamento.util.TextoUtil.convertStringToByte;

@AllArgsConstructor
@Component
public class PautaMapper implements AbstractMapper<Pauta, PautaRequestDto> {

  @Override
  public PautaRequestDto entidadeParaDTO(Pauta entidade) {
    return PautaRequestDto.builder()
        .id(entidade.getId())
        .theme(entidade.getTema())
        .subject(convertStringToByte(entidade.getAssunto()))
        .build();
  }

  @Override
  public Pauta dtoParaEntidade(PautaRequestDto pautaRequestDto) {
    return Pauta.builder()
        .tema(pautaRequestDto.getTheme())
        .assunto(pautaRequestDto.getSubject().getBytes())
        .build();
  }
}
