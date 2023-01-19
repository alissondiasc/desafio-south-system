package br.com.julgamento.resource;

import br.com.julgamento.model.request.VotoParticipacaoRequestDto;
import br.com.julgamento.service.VotoParticipacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("voto-participacao")
@Api(
    tags = { "Controle de votação" }
)
public class VotoParticipacaoResource {

  private final VotoParticipacaoService votoParticipacaoService;

  @ApiOperation(value = "Realizar voto na sessão de julgamento para alguma pauta")
  @PostMapping
  public ResponseEntity create(@RequestBody @Valid VotoParticipacaoRequestDto votoParticipacaoRequestDto) {
    votoParticipacaoService.create(votoParticipacaoRequestDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @ApiOperation(value = "Obter todos os votos efetuados")
  @GetMapping
  public ResponseEntity getVotes(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "conut", defaultValue = "10") Integer count,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
      @RequestParam(value = "orderBy", defaultValue = "voto") String orderBy
  ) {
    Pageable pageable = PageRequest.of(page, count, Sort.Direction.valueOf(direction), orderBy);
    return ResponseEntity.ok(votoParticipacaoService.getVotesPageable(pageable));
  }
}
