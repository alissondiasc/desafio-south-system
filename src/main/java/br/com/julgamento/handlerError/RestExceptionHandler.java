package br.com.julgamento.handlerError;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ UnprocessableEntityException.class })
  public ResponseEntity<ApiError> unprocessableEntity(final UnprocessableEntityException ex,
      final HttpServletRequest request) {
    String uri = this.maskURI(request.getRequestURI());
    log.warn("{} em {}", ex.getClass().getSimpleName(), uri);
    ResponseEntity<ApiError> response = this.createResponseEntity(request.getRequestURI(),
        HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    return response;
  }

  @ExceptionHandler({ ValidationException.class })
  public ResponseEntity<ApiError> validationException(final UnprocessableEntityException ex,
      final HttpServletRequest request) {
    String uri = this.maskURI(request.getRequestURI());
    log.warn("{} em {}", ex.getClass().getSimpleName(), uri);
    ResponseEntity<ApiError> response = this.createResponseEntity(request.getRequestURI(),
        HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    return response;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    List<ErrorObject> errors = getErrors(ex);
    ErrorResponse errorResponse = getErrorResponse(ex, status, errors);

    return new ResponseEntity<>(errorResponse, status);
  }

  private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
      List<ErrorObject> errors) {
    return new ErrorResponse("Requisição possui campos inválidos", status.value(),
        status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
  }

  private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getFieldErrors().stream()
        .map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
        .collect(Collectors.toList());
  }

  public String maskURI(String uri) {
    return uri.replaceAll("[\n\r\t\\|]", "_");
  }

  private ResponseEntity<ApiError> createResponseEntity(String path, HttpStatus status, String description) {
    return this.createResponseEntity(path, status.value(), description);
  }

  private ResponseEntity<ApiError> createResponseEntity(String path, int status, String description) {
    ApiError error = new ApiError(path, status, description);
    return ResponseEntity.status(status).body(error);
  }
}
