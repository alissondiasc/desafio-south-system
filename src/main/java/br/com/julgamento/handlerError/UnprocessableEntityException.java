package br.com.julgamento.handlerError;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
  private String description;

  public UnprocessableEntityException(String message) {
    super(message);
  }

  public UnprocessableEntityException(String description, String message) {
    super(message);
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
