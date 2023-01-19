package br.com.julgamento.service.impl;

import br.com.julgamento.model.response.PautaRequestDto;
import br.com.julgamento.repository.PautaRepository;
import br.com.julgamento.service.PautaService;
import br.com.julgamento.service.mapper.PautaMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class PautaServiceImpl implements PautaService {

  private PautaRepository pautaRepository;
  private PautaMapper pautaMapper;

  @Override
  public void cadastrar(PautaRequestDto pautaRequestDto) {
    pautaRepository.save(pautaMapper.dtoParaEntidade(pautaRequestDto));
  }

  @Override
  public Page<PautaRequestDto> obterPautas(Pageable pageable) {
    return pautaMapper.pageEntidadeParaPageDTO(pautaRepository.findAll(pageable));
  }
}
