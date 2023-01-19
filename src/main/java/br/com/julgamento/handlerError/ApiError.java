package br.com.julgamento.handlerError;

import java.util.List;

import static java.util.Arrays.asList;

public class ApiError {
  private String path;
  private int status;
  private String description;
  private List<String> errors;

  public ApiError() {
  }

  public ApiError(String path, int status, String description) {
    this.path = path;
    this.status = status;
    this.description = description;
    this.errors = asList(description);
  }

  public ApiError(String path, int status, String description, List<String> errors) {
    this.path = path;
    this.status = status;
    this.description = description;
    this.errors = errors;
  }
}
