package br.com.julgamento.service.mapper;

import br.com.julgamento.model.entity.Usuario;
import br.com.julgamento.model.response.UserCreateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioMapper implements AbstractMapper<Usuario, UserCreateRequestDto> {

  @Override
  public UserCreateRequestDto entidadeParaDTO(Usuario entidade) {
    return UserCreateRequestDto.builder()
        .id(entidade.getId())
        .name(entidade.getNome())
        .document(entidade.getCpf())
        .build();
  }

  @Override
  public Usuario dtoParaEntidade(UserCreateRequestDto entidade) {
    return Usuario.builder()
        .nome(entidade.getName())
        .cpf(entidade.getDocument())
        .build();
  }

}
