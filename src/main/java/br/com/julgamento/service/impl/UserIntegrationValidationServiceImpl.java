package br.com.julgamento.service.impl;

import br.com.julgamento.client.userIntegrationValidation.UserIntegrationValidation;
import br.com.julgamento.client.userIntegrationValidation.response.UserIntegrationValidationResponse;
import br.com.julgamento.handlerError.UnprocessableEntityException;
import br.com.julgamento.service.UserIntegrationValidationService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserIntegrationValidationServiceImpl implements UserIntegrationValidationService {
  private final UserIntegrationValidation userIntegrationValidation;

  public boolean validateCpfIntegration(final String document) {
    try {
      UserIntegrationValidationResponse userIntegrationValidationResponse = userIntegrationValidation
          .validarCPF(document);
      return userIntegrationValidationResponse.isValido();

    } catch (FeignException.NotFound e) {
      throw new UnprocessableEntityException("CPF inválido");
    }
  }
}
