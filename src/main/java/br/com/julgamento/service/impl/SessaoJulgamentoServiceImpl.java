package br.com.julgamento.service.impl;

import br.com.julgamento.model.entity.SessaoJulgamento;
import br.com.julgamento.model.entity.enums.Indicador;
import br.com.julgamento.model.request.SessaoJulgamentoRequest;
import br.com.julgamento.repository.SessaoJulgamentoRepository;
import br.com.julgamento.service.SessaoJulgamentoService;
import br.com.julgamento.service.mapper.SessaoJulgamentoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.connect.errors.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
@Service
@Slf4j
public class SessaoJulgamentoServiceImpl implements SessaoJulgamentoService {

  public static final String MSG_VALIDATION_DATA_FIM = "Data fim não pode ser anterior a data e hora atual.";
  public static final String MSG_VALIDATION_DATA_INICIO = "Data de início não pode ser anterior a data e hora atual.";
  public static final String TRIAL_SESSION_NOT_FOUND = "Não existe sessão julgamento com esse id";
  private final SessaoJulgamentoRepository sessaoJulgamentoRepository;
  private final SessaoJulgamentoMapper sessaoJulgamentoMapper;

  @Override
  public void create(final SessaoJulgamentoRequest sessaoJulgamentoRequest) {
    dateValidation(sessaoJulgamentoRequest.getDataFim(), MSG_VALIDATION_DATA_FIM);
    dateValidation(sessaoJulgamentoRequest.getDataInicio(), MSG_VALIDATION_DATA_INICIO);
    SessaoJulgamento sessaoJulgamento = sessaoJulgamentoMapper.dtoParaEntidade(sessaoJulgamentoRequest);
    atualizarIndicador(sessaoJulgamento, Indicador.S);
  }

  public void dateValidation(final LocalDateTime data, final String msg) {
    if (nonNull(data)) {
      LocalDateTime now = LocalDateTime.now();
      if (data.isBefore(now)) {
        throw new ValidationException(msg);
      }
    }
  }

  @Override
  public SessaoJulgamento getTrialSession(final String idTrialSession) {
    return sessaoJulgamentoRepository
        .findById(idTrialSession)
        .orElseThrow(() -> new NotFoundException(TRIAL_SESSION_NOT_FOUND));
  }

  @Override
  public void atualizarIndicador(final SessaoJulgamento trialSession, final Indicador indicator) {
    trialSession.setIndSessaoAberta(indicator);
    sessaoJulgamentoRepository.save(trialSession);
  }

  @Override
  public Page<SessaoJulgamentoRequest> getTrialSessionPageable(Pageable pageable) {
    return sessaoJulgamentoMapper.pageEntidadeParaPageDTO(sessaoJulgamentoRepository.findAll(pageable));
  }
}
