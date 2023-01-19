package br.com.julgamento.model.entity;

import br.com.julgamento.model.entity.enums.Indicador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SESSAO_JULGAMENTO")
public class SessaoJulgamento implements Serializable {

  private static final long serialVersionUID = 3L;

  private String id;
  @DBRef
  private Pauta pauta;
  private LocalDateTime dataInicio;
  private LocalDateTime dataFim;
  private Indicador indSessaoAberta;
}
