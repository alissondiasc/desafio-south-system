package br.com.julgamento.service;

import br.com.julgamento.model.response.UserCreateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioSevice {

  void create(UserCreateRequestDto userCreateRequestDto);

  Page<UserCreateRequestDto> obterUsuario(Pageable pageable);
}
