package br.com.julgamento.model.entity;

import br.com.julgamento.model.entity.enums.ResultadoVotacao;
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
@Document(collection = "RESULTADO_JULGAMENTO")
public class ResultadoJulgamento implements Serializable {
  private static final long serialVersionUID = 2L;
  @Id
  private String id;
  @DBRef
  private SessaoJulgamento sessaoJulgamento;
  private ResultadoVotacao resultadoVotacao;

}
