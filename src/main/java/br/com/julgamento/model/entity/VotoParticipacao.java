package br.com.julgamento.model.entity;

import br.com.julgamento.model.entity.enums.Indicador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "VOTO_PARTICIPACAO")
public class VotoParticipacao implements Serializable {

  private static final long serialVersionUID = 5L;

  @Id
  private String id;
  @DBRef
  private Usuario associado;
  @DBRef
  private SessaoJulgamento sessaoJulgamento;
  private Indicador voto;
}
