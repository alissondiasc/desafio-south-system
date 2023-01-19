package br.com.julgamento.resource;

import br.com.julgamento.model.request.SessaoJulgamentoRequest;
import br.com.julgamento.service.SessaoJulgamentoService;
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
@SuppressWarnings({ "java:S4834", "squid:S4834" })
@RequestMapping("sessao-julgamento")
@Api(
    tags = { "Sessão de Julgamento" }
)
public class SessaoJulgamentoResource {

  private SessaoJulgamentoService sessaoJulgamentoService;

  @ApiOperation(value = "Criar sessão de julgamento")
  @PostMapping
  public ResponseEntity cadastrarSessaoJulgamento(
      @RequestBody @Valid SessaoJulgamentoRequest sessaoJulgamentoRequest) {
    sessaoJulgamentoService.create(sessaoJulgamentoRequest);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Obter sessoes cadastrados")
  @GetMapping
  public ResponseEntity<Page<SessaoJulgamentoRequest>> obterSessoesCadastradas(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "conut", defaultValue = "10") Integer count,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
  ) {
    Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(direction), orderBy);
    return ResponseEntity.ok(sessaoJulgamentoService.getTrialSessionPageable(pageable));
  }

}
