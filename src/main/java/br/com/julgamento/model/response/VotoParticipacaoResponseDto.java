package br.com.julgamento.model.response;

import br.com.julgamento.model.entity.enums.Indicador;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class VotoParticipacaoResponseDto {

  @NotNull(message = "Campo idJulgamento é de preenchimento obrigatório")
  private String idJulgamento;
  @NotNull(message = "Campo idAssociado é de preenchimento obrigatório")
  private String idAssociado;
  @NotNull(message = "Campo votoParticipacao é de preenchimento obrigatório")
  private Indicador votoParticipacao;
  @ApiModelProperty(hidden = true)
  private String nomeAssociado;
  @ApiModelProperty(hidden = true)
  private String dataHoraSessaoJulgamento;
  @ApiModelProperty(hidden = true)
  private String pautaJulgada;
}
