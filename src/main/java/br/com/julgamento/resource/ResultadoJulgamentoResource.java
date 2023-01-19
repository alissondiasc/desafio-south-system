package br.com.julgamento.resource;

import br.com.julgamento.service.ResultadoJulgamentoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("resultado-julgamento")
@Api(
    tags = { "Resultado de Julgamento" }
)
public class ResultadoJulgamentoResource {

  private final ResultadoJulgamentoService resultadoJulgamentoService;

  @ApiOperation(value = "Encerrar e retornar sessão de julgamento")
  @PostMapping(value = "encerrar-sessao/{idSessaoJulgamento}")
  public ResponseEntity endTrialSession(@PathVariable String idSessaoJulgamento) throws Exception {
    return ResponseEntity.ok(resultadoJulgamentoService.endTrialSessionAndCreatResulTrialSession(idSessaoJulgamento));
  }

  @ApiOperation(value = "Encerrar e retornar sessão de julgamento")
  @GetMapping(value = "obter-detalhes-resultado/{idSessaoJulgamento}")
  public ResponseEntity obterDetalhesResultado(@PathVariable String idSessaoJulgamento) throws Exception {
    return ResponseEntity.ok(resultadoJulgamentoService.obterDetalhesResultadoJulgamento(idSessaoJulgamento));
  }

  @ApiOperation(value = "Obter votos efetuados")
  @GetMapping
  public ResponseEntity getResultsJudgment(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "conut", defaultValue = "10") Integer count,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "voto") String orderBy
  ) {
    Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(direction), orderBy);
    return ResponseEntity.ok(resultadoJulgamentoService.getResultsJudgment(pageable));
  }
}
