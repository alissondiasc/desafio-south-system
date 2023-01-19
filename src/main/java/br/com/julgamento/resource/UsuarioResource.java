package br.com.julgamento.resource;

import br.com.julgamento.model.response.UserCreateRequestDto;
import br.com.julgamento.service.UserIntegrationValidationService;
import br.com.julgamento.service.UsuarioSevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("usuario")
@Api(
    tags = { "Usuário" }
)
public class UsuarioResource {

  private UsuarioSevice usuarioSevice;
  private UserIntegrationValidationService userIntegrationValidationService;

  @ApiOperation(value = "Criar Associado")
  @PostMapping
  public ResponseEntity create(@RequestBody @Valid UserCreateRequestDto userCreateRequestDto) {
    usuarioSevice.create(userCreateRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Verificar validade de CPF via integração com Heroku")
  @GetMapping(value = "valida-cpf/{cpf}")
  public ResponseEntity validationCPF(@PathVariable String cpf) {
    return ResponseEntity.ok(userIntegrationValidationService.validateCpfIntegration(cpf));
  }

  @ApiOperation(value = "Obter associados cadastrados")
  @GetMapping
  public ResponseEntity<Page<UserCreateRequestDto>> getUsers(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "conut", defaultValue = "10") Integer count,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
  ) {
    Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(direction), orderBy);
    return ResponseEntity.ok(usuarioSevice.obterUsuario(pageable));
  }
}
