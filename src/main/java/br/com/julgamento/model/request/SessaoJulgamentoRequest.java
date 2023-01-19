package br.com.julgamento.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class SessaoJulgamentoRequest {
  @ApiModelProperty(hidden = true)
  private String id;
  @NotBlank(message = "O campo idPauta é de preenchimento obrigatório")
  private String idPauta;
  @ApiModelProperty(hidden = true)
  private String temaPauta;
  @ApiModelProperty(example = "2012-12-31T16:40")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
  private LocalDateTime dataInicio;
  @ApiModelProperty(example = "2012-12-31T16:40")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "America/Sao_Paulo")
  private LocalDateTime dataFim;
}
