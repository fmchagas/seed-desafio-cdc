package br.com.fmchagas.desafiocdc.autor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>, AutorRepositoryGatway{

	Optional<Autor> findByEmail(String email);

}
