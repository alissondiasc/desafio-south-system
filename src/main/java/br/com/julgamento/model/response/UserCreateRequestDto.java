package br.com.julgamento.model.response;

import br.com.julgamento.util.annotations.UnicoCPF;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserCreateRequestDto {

  @ApiModelProperty(hidden = true)
  private String id;

  @ApiModelProperty(
      value = "Nome do usuário",
      example = "Alisson Dias da Cruz"
  )
  @JsonProperty("nome")
  @Valid
  @NotNull(message = "O campo nome é de preenchimento obrigatório.")
  private String name;

  @ApiModelProperty(
      value = "CPF do usuário",
      example = "504.890.710-82"
  )
  @JsonProperty("cpf")
  @Valid
  @NotNull(message = "O campo CPF é de preenchimento obrigatório.")
  @UnicoCPF(message = "Já existe um usuário com esse CPF.")
  @Size(min = 11, max = 11, message = "O campo CPF deve deve conter exatos 11 numeros")
  @Pattern(regexp = "\\d+", message = "O campo CPF deve conter apenas numeros")
  private String document;
}
