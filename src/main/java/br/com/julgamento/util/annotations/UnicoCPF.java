package br.com.julgamento.util.annotations;

import br.com.julgamento.util.validation.UnicoCPFValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UnicoCPFValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicoCPF {

  String message() default "CPF duplicado";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
