package br.com.julgamento.model.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Domínio para os campos do tipo S/N.
 */
@Getter
@RequiredArgsConstructor
public enum Indicador {

  S(Boolean.TRUE),
  N(Boolean.FALSE);

  @Getter
  private final Boolean valor;

  public static Indicador getIndicador(Boolean valor) {
    for (Indicador indicador : Indicador.values()) {
      if (indicador.getValor().equals(valor)) {
        return indicador;
      }
    }
    return null;
  }

  public Indicador not() {
    return Indicador.getIndicador(!this.valor);
  }

}

