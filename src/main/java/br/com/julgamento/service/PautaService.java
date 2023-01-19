package br.com.julgamento.service;

import br.com.julgamento.model.response.PautaRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface PautaService {
  void cadastrar(PautaRequestDto pautaRequestDto);

  Page<PautaRequestDto> obterPautas(Pageable page);
}
