package br.com.julgamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableFeignClients
@EnableKafka
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class JulgamentoEletronicoApplication {

  public static void main(String[] args) {
    SpringApplication.run(JulgamentoEletronicoApplication.class, args);
  }

}
