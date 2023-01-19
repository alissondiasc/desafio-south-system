package br.com.julgamento.service.impl;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.Usuario;
import br.com.julgamento.model.entity.VotoParticipacao;
import br.com.julgamento.model.entity.enums.ResultadoVotacao;
import br.com.julgamento.model.request.VotoParticipacaoRequestDto;
import br.com.julgamento.model.response.VotoParticipacaoResponseDto;
import br.com.julgamento.repository.VotoParticipacaoRepository;
import br.com.julgamento.service.SessaoJulgamentoService;
import br.com.julgamento.service.VotoParticipacaoService;
import br.com.julgamento.service.mapper.VotoParticipacaoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.errors.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class VotoParticipacaoServiceImpl implements VotoParticipacaoService {

  public static final String TRIAL_SESSION_CLOSED = "Sessão de julgamento já encerrada.";
  public static final String VERIFICATION_SINGLE_VOTE = "Não é possivel votar duas vezes para mesma sessão.";
  public static final String VOTES_TRIAL_SESSION_NOT_FOUND = "Não há votos a serem computados para esta sessão de julgamento";
  private final VotoParticipacaoRepository votoParticipacaoRepository;
  private final VotoParticipacaoMapper votoParticipacaoMapper;
  private final SessaoJulgamentoService sessaoJulgamentoService;

  @Override
  public void create(final VotoParticipacaoRequestDto votoParticipacaoRequestDto) {
    checkClosedSession(votoParticipacaoRequestDto);
    verifySingleVote(votoParticipacaoRequestDto);
    votoParticipacaoRepository.save(votoParticipacaoMapper.dtoParaEntidade(votoParticipacaoRequestDto));
  }

  @Override
  public ResultadoVotacao countVotesTrialSession(final String idSessaoJulgamento) {
    final SessaoJulgamento sessaoJulgamento = SessaoJulgamento.builder().id(idSessaoJulgamento).build();
    final List<VotoParticipacao> votoParticipacaos = Optional
        .ofNullable(votoParticipacaoRepository.findBySessaoJulgamento(sessaoJulgamento))
        .orElseThrow(() -> new NotFoundException(VOTES_TRIAL_SESSION_NOT_FOUND));
    long votosContra = votoParticipacaos.stream().filter(votoParticipacao -> !votoParticipacao.getVoto().getValor())
        .count();
    long votosAFavor = votoParticipacaos.stream().filter(votoParticipacao -> votoParticipacao.getVoto().getValor())
        .count();

    if (votosContra > votosAFavor) {
      return ResultadoVotacao.P;
    } else if (votosAFavor > votosContra) {
      return ResultadoVotacao.V;
    } else {
      return ResultadoVotacao.E;
    }
  }

  @Override
  public Page<VotoParticipacaoResponseDto> getVotesPageable(final Pageable pageable) {
    return votoParticipacaoMapper.pageEntidadeParaPageDTO(votoParticipacaoRepository.findAll(pageable));
  }

  public void checkClosedSession(final VotoParticipacaoRequestDto votoParticipacaoRequestDto) {
    SessaoJulgamento sessaoJulgamento = sessaoJulgamentoService.getTrialSession(
        votoParticipacaoRequestDto.getIdJulgamento());
    LocalDateTime dataHoraAtual = LocalDateTime.now();
    if (sessaoJulgamento.getDataFim().isBefore(dataHoraAtual) || !sessaoJulgamento.getIndSessaoAberta().getValor()) {
      throw new ValidationException(TRIAL_SESSION_CLOSED);
    }
  }

  public void verifySingleVote(final VotoParticipacaoRequestDto votoParticipacaoRequestDto) {
    Usuario associado = Usuario.builder().id(votoParticipacaoRequestDto.getIdAssociado()).build();
    SessaoJulgamento sessaoJulgamento = SessaoJulgamento.builder().id(votoParticipacaoRequestDto.getIdJulgamento())
        .build();
    if (votoParticipacaoRepository.existsByAssociadoAndSessaoJulgamento(associado, sessaoJulgamento)) {
      throw new ValidationException(VERIFICATION_SINGLE_VOTE);
    }
  }
}
