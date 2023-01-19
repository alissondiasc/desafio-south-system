package br.com.julgamento.util.validation;

import br.com.julgamento.repository.UsuarioRepository;
import br.com.julgamento.util.annotations.UnicoCPF;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoCPFValidator implements ConstraintValidator<UnicoCPF, String> {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Override
  public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
    return !usuarioRepository.findByCpf(cpf).isPresent();
  }
}
