package br.com.julgamento.model.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultadoVotacao {

  V("VENCEDORA"),
  P("PERDEDORA"),
  E("EMPATE");

  private final String valor;

  public static ResultadoVotacao getEnvioAberturaPubPautaEnum(String valor) {
    for (ResultadoVotacao envioEnum : ResultadoVotacao.values()) {
      if (envioEnum.getValor().equals(valor)) {
        return envioEnum;
      }
    }
    return null;
  }

  public static ResultadoVotacao getValue(final String valorRecebido) {
    for (ResultadoVotacao envioEnum : ResultadoVotacao.values()) {
      if (envioEnum.getValor().equals(valorRecebido)) {
        return envioEnum;
      }
    }
    return null;
  }
}
