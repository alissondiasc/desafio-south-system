package br.com.julgamento.client.userIntegrationValidation;

import br.com.julgamento.client.userIntegrationValidation.response.UserIntegrationValidationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "valida-cpf", url = "${usuario.client.url}")
public interface UserIntegrationValidation {

  @GetMapping(value = "/{cpf}")
  UserIntegrationValidationResponse validarCPF(@PathVariable String cpf);
}
