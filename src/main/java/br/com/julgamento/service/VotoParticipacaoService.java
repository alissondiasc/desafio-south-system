package br.com.julgamento.service;

import br.com.julgamento.model.entity.enums.ResultadoVotacao;
import br.com.julgamento.model.request.VotoParticipacaoRequestDto;
import br.com.julgamento.model.response.VotoParticipacaoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface VotoParticipacaoService {

  void create(VotoParticipacaoRequestDto votoParticipacaoRequestDto);

  ResultadoVotacao countVotesTrialSession(String idSessaoJulgamento);

  Page<VotoParticipacaoResponseDto> getVotesPageable(Pageable pageable);
}
