package br.com.fmchagas.desafiocdc.livro;

import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fmchagas.desafiocdc.livro.detalhe.LivroDetalheResponse;

@RestController
@RequestMapping("/api/v1/livros")
//total carga: 5
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

		final LivroResponse livroResponse = new LivroResponse(livro);
		
		URI uri = uriBuilder.path("/api/v1/livros/{id}").buildAndExpand(livro.getId()).toUri();
		
		return ResponseEntity.created(uri).body(livroResponse);
	}
	
	@GetMapping("/detalhe/{id}")
	//1
	public ResponseEntity<LivroDetalheResponse> detalhe(@PathVariable Long id) {
		Livro livro = manager.find(Livro.class, id);
		
		//1
		if(livro==null) {
			return ResponseEntity.notFound().build();
		}
		
		LivroDetalheResponse livroResponse = new LivroDetalheResponse(livro);

		return ResponseEntity.ok(livroResponse);
	}

}
