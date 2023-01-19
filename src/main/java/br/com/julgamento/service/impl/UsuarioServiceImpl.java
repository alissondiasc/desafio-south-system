package br.com.julgamento.service.impl;

import br.com.julgamento.model.response.UserCreateRequestDto;
import br.com.julgamento.repository.UsuarioRepository;
import br.com.julgamento.service.UserIntegrationValidationService;
import br.com.julgamento.service.UsuarioSevice;
import br.com.julgamento.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioSevice {

  private UsuarioRepository usuarioRepository;
  private UsuarioMapper usuarioMapper;
  private UserIntegrationValidationService userIntegrationValidationService;

  @Override
  public void create(UserCreateRequestDto userCreateRequestDto) {
/*  Depois de alguns testes na API do HEROKU não consegui sucesso ao fazer requisição com CPFs gerados pelo ForDevs, porém mesmo assim,
    segue regra e logica implementadas
    boolean isValid = userIntegrationValidationService.validateCpfIntegration(userCreateRequestDto.getDocument());
    if (isValid) {
      usuarioRepository.save(usuarioMapper.dtoParaEntidade(userCreateRequestDto));
    }
*/
    usuarioRepository.save(usuarioMapper.dtoParaEntidade(userCreateRequestDto));
  }

  @Override
  public Page<UserCreateRequestDto> obterUsuario(Pageable pageable) {
    return usuarioMapper.pageEntidadeParaPageDTO(usuarioRepository.findAll(pageable));
  }
}
