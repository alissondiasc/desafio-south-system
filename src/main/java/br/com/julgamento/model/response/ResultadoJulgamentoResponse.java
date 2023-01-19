package br.com.julgamento.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ResultadoJulgamentoResponse {

  private String id;
  @NotBlank(message = "Campo idJulgamento é de preenchimento obrigatório.")
  private String idJulgamento;
  private String resultadoVotacao;
}
