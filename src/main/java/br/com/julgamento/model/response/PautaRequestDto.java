package br.com.julgamento.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Builder
public class PautaRequestDto {

  @ApiModelProperty(hidden = true)
  private String id;

  @ApiModelProperty(
      value = "Descrição do que será votado se sim ou se não na pauta",
      example = "Pauta para diminuir salarios dos colaboradores"
  )
  @NotNull(message = "Campo tema é de preenchimento obrigatório")
  @JsonProperty("tema")
  private String theme;

  @ApiModelProperty(
      value = "Breve resumo do tema da pauta.",
      example = "Pauta para descobrir junto a todos colaboradores se é necessário diminuirmos nossos salarios para manter empresa ativa."
  )
  @NotNull(message = "Campo assunto é de preenchimento obrigatório")
  @JsonProperty("assunto")
  private String subject;
}
