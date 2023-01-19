package br.com.julgamento.service.impl;

import br.com.julgamento.kafkaService.Producer;
import br.com.julgamento.model.entity.ResultadoJulgamento;
import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.VotoParticipacao;
import br.com.julgamento.model.entity.enums.Indicador;
import br.com.julgamento.model.entity.enums.ResultadoVotacao;
import br.com.julgamento.model.response.DetalhesResultadoJulgamentoResponse;
import br.com.julgamento.model.response.ResultadoJulgamentoResponse;
import br.com.julgamento.repository.ResultadoJulgamentoRepository;
import br.com.julgamento.repository.SessaoJulgamentoRepository;
import br.com.julgamento.repository.VotoParticipacaoRepository;
import br.com.julgamento.service.ResultadoJulgamentoService;
import br.com.julgamento.service.VotoParticipacaoService;
import br.com.julgamento.service.mapper.PautaMapper;
import br.com.julgamento.service.mapper.ResultadoJulgamentoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.errors.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

//import org.springframework.kafka

@RequiredArgsConstructor
@Service
@Slf4j
public class ResultadoJulgamentoImpl implements ResultadoJulgamentoService {

  public static final String TRIAL_SESSION_NOT_FOUND = "Não existe sessão julgamento com esse ID: ";
  public static final String RESULT_TRIAL_SESSION_NOT_FOUND = "Não existe resultado de julgamento com esse ID: ";
  private final Producer topicProducer;
  private final ResultadoJulgamentoRepository resultadoJulgamentoRepository;
  private final SessaoJulgamentoRepository sessaoJulgamentoRepository;
  private final VotoParticipacaoRepository votoParticipacaoRepository;
  private final VotoParticipacaoService votoParticipacaoService;
  private final ResultadoJulgamentoMapper resultadoJulgamentoMapper;
  private final PautaMapper pautaMapper;

  @Override
  public ResultadoJulgamentoResponse create(final String idJulgamentoSessao) {
    ResultadoVotacao resultadoVotacao = votoParticipacaoService.countVotesTrialSession(idJulgamentoSessao);
    ResultadoJulgamento resultadoJulgamento = ResultadoJulgamento.builder()
        .sessaoJulgamento(SessaoJulgamento.builder().id(idJulgamentoSessao).build()).build();
    resultadoJulgamento.setResultadoVotacao(resultadoVotacao);
    resultadoJulgamentoRepository.save(resultadoJulgamento);
    return resultadoJulgamentoMapper.entidadeParaDTO(resultadoJulgamento);
  }

  @Override
  public ResultadoJulgamentoResponse endTrialSessionAndCreatResulTrialSession(final String idSessaoJulgamento) {
    final SessaoJulgamento sessaoJulgamento = sessaoJulgamentoRepository
        .findById(idSessaoJulgamento)
        .orElseThrow(() -> new NotFoundException(TRIAL_SESSION_NOT_FOUND + idSessaoJulgamento));
    final ResultadoJulgamentoResponse resultadoJulgamentoResponse = create(idSessaoJulgamento);
    sessaoJulgamento.setIndSessaoAberta(Indicador.N);
    sessaoJulgamentoRepository.save(sessaoJulgamento);
    topicProducer.send(resultadoJulgamentoResponse);
    return resultadoJulgamentoResponse;
  }

  @Override
  public DetalhesResultadoJulgamentoResponse obterDetalhesResultadoJulgamento(final String idResultado) {
    final ResultadoJulgamento resultadoJulgamento = resultadoJulgamentoRepository
        .findById(idResultado)
        .orElseThrow(() -> new NotFoundException(RESULT_TRIAL_SESSION_NOT_FOUND + idResultado));
    final List<VotoParticipacao> votoParticipacaos = votoParticipacaoRepository.findBySessaoJulgamento(
        SessaoJulgamento.builder().id(resultadoJulgamento.getSessaoJulgamento().getId()).build());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    return DetalhesResultadoJulgamentoResponse.builder()
        .idJulgamento(resultadoJulgamento.getSessaoJulgamento().getId())
        .dataJulgamento(resultadoJulgamento.getSessaoJulgamento().getDataInicio().format(formatter))
        .pautaRequestDto(pautaMapper.entidadeParaDTO(resultadoJulgamento.getSessaoJulgamento().getPauta()))
        .resultadojulamento(resultadoJulgamento.getResultadoVotacao().getValor())
        .votosContras(
            votoParticipacaos.stream().filter(votoParticipacao -> !votoParticipacao.getVoto().getValor()).count())
        .votosFavoraveis(
            votoParticipacaos.stream().filter(votoParticipacao -> votoParticipacao.getVoto().getValor()).count())
        .build();
  }

  @Override
  public Page<ResultadoJulgamentoResponse> getResultsJudgment(Pageable pageable) {
    return resultadoJulgamentoMapper.pageEntidadeParaPageDTO(resultadoJulgamentoRepository.findAll(pageable));
  }
}
