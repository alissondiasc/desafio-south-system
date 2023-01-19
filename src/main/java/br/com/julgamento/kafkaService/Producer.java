package br.com.julgamento.kafkaService;

import br.com.julgamento.model.response.ResultadoJulgamentoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;
  @Value("${topic.name.producer}")
  private String topicName;

  public void send(ResultadoJulgamentoResponse resultadoJulgamentoResponse) {
    final String message = objectToJson(resultadoJulgamentoResponse);
    log.info("Payload enviado: {resultadoJulgamento}", message);
    kafkaTemplate.send(topicName, message);
  }

  private String objectToJson(ResultadoJulgamentoResponse resultadoJulgamentoResponse) {
    try {
      return objectMapper.writeValueAsString(resultadoJulgamentoResponse);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
