package br.com.julgamento.resource;

import br.com.julgamento.model.response.PautaRequestDto;
import br.com.julgamento.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("pauta")
@Api(
    tags = { "Pauta de Julgamento" }
)
public class PautaResource {
  private final PautaService pautaService;

  @ApiOperation(value = "Criar pauta de julgamento")
  @PostMapping
  public ResponseEntity create(@RequestBody @Valid PautaRequestDto pautaRequestDto) {
    pautaService.cadastrar(pautaRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Obter pautas cadastrados")
  @GetMapping
  public ResponseEntity<Page<PautaRequestDto>> obterPautas(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "conut", defaultValue = "10") Integer count,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "tema") String orderBy
  ) {
    Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(direction), orderBy);
    return ResponseEntity.ok(pautaService.obterPautas(pageable));
  }
}
