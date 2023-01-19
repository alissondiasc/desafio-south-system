package br.com.julgamento.repository;

import br.com.julgamento.model.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
  Optional<Usuario> findByCpf(String cpf);

}
