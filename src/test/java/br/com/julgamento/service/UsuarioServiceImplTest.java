package br.com.julgamento.service;

import br.com.julgamento.client.userIntegrationValidation.UserIntegrationValidation;
import br.com.julgamento.client.userIntegrationValidation.response.UserIntegrationValidationResponse;
import br.com.julgamento.model.entity.Usuario;
import br.com.julgamento.model.response.UserCreateRequestDto;
import br.com.julgamento.repository.UsuarioRepository;
import br.com.julgamento.service.impl.UsuarioServiceImpl;
import br.com.julgamento.service.mapper.UsuarioMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UsuarioServiceImplTest {

  @InjectMocks
  private UsuarioServiceImpl usuarioSevice;
  @Mock
  private UserIntegrationValidation userIntegrationValidation;
  @Mock
  private UsuarioMapper usuarioMapper;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private UserCreateRequestDto userCreateRequestDto;
  @Mock
  private Pageable pageable;
  @Mock
  private UserIntegrationValidationResponse userIntegrationValidationResponseValid;
  @Mock
  private UserIntegrationValidationResponse userIntegrationValidationResponseInvalid;

  @Before
  public void setUp() {
    pageable = PageRequest.of(0, 10, Sort.Direction.valueOf("ASC"), "nome");
    userIntegrationValidationResponseValid = UserIntegrationValidationResponse.builder()
        .status("ABLE_TO_VOTE")
        .build();
    userIntegrationValidationResponseInvalid = UserIntegrationValidationResponse.builder()
        .status("UNABLE_TO_VOTE")
        .build();

    userCreateRequestDto = UserCreateRequestDto.builder()
        .document("037823336098")
        .name("Teste Sucesso")
        .build();

  }

  //    @Test
  //    public void cadastrarTest() {
  //
  //        when(userIntegrationValidation.validarCPF(userCreateRequestDto.getDocument())).thenReturn(
  //            userIntegrationValidationResponseInvalid);
  //        String retorno = usuarioSevice.create(this.userCreateRequestDto);
  //        assertThat(retorno).isNotNull();
  //        assertEquals(retorno, "CPF inválido.");
  //    }
  //
  //    @Test
  //    public void cadastrarCPFInvalidoTest() {
  //
  //        when(userIntegrationValidation.validarCPF(userCreateRequestDto.getDocument())).thenReturn(
  //            userIntegrationValidationResponseValid);
  //        String retorno = usuarioSevice.create(this.userCreateRequestDto);
  //        assertThat(retorno).isNotNull();
  //        assertEquals(retorno, "Usuario criado com sucesso.");
  //    }

  @Test
  public void obterUsuarioTest() {

    when(usuarioRepository.findAll(pageable)).thenReturn(
        new PageImpl<>(Arrays.asList(Usuario.builder().build()), pageable, 1));
    when(usuarioMapper.pageEntidadeParaPageDTO(
        new PageImpl<>(Arrays.asList(Usuario.builder().build()), pageable, 1))).thenReturn(new PageImpl<>(Arrays.asList(
        UserCreateRequestDto.builder().build()), pageable, 1));
    Page<UserCreateRequestDto> retorno = usuarioSevice.obterUsuario(pageable);
    assertThat(retorno).isNotNull();
    assertEquals(retorno.getContent().size(), 1);
  }

}
