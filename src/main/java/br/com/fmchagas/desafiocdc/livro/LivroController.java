package br.com.fmchagas.desafiocdc.livro;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/livros")
//total carga: 4
public class LivroController {
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	//1
	public ResponseEntity<List<LivroComIdENome>> lista() {
		List<Livro> livros = manager
				.createQuery("select l from Livro l", Livro.class)
				.getResultList();
		
		List<LivroComIdENome> livrosResponse = LivroComIdENome.converter(livros);

		return ResponseEntity.ok(livrosResponse);
	}
	
	
	@PostMapping
	@Transactional
	//1
	public ResponseEntity<LivroResponse> novo(@Valid @RequestBody LivroRequest request, UriComponentsBuilder uriBuilder){
		//1
		final Livro livro = manager.merge(request.toModel(manager));
		//1
		final LivroResponse livroResponse = new LivroResponse(livro);
		
		URI uri = uriBuilder.path("/api/v1/livros/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livroResponse);
	}

}
