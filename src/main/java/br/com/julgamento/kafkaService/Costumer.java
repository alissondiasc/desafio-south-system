package br.com.julgamento.kafkaService;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.response.ResultadoJulgamentoResponse;
import br.com.julgamento.service.SessaoJulgamentoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class Costumer {
  private final ObjectMapper objectMapper;
  private final SessaoJulgamentoService sessaoJulgamentoService;
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  @KafkaListener(topics = "${topic.name.producer}", groupId = "${spring.kafka.costumer.group-id}")
  public void lerMenssagemResultadoJulgamento(final String messagem) {
    try {
      ResultadoJulgamentoResponse resultadoJulgamentoResponse = objectMapper.readValue(messagem,
          ResultadoJulgamentoResponse.class);
      SessaoJulgamento sessaoJulgamento = sessaoJulgamentoService.getTrialSession(
          resultadoJulgamentoResponse.getIdJulgamento());
      log.info(
          "A Sessão de julgamento de numero: " + sessaoJulgamento.getId() + "do dia " + sessaoJulgamento.getDataInicio()
              .format(
                  formatter) + "\n" + " foi encerrada. Seus resultado é: " + resultadoJulgamentoResponse.getResultadoVotacao());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
