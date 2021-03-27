package br.com.fmchagas.desafiocdc.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categorias")
//Carga total: 2
public class CategoriaController {
	
	@PersistenceContext private EntityManager manager;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	@Transactional
	//1
	public String nova(@Valid @RequestBody CategoriaRequest request) {
		//1
		final Categoria categoria = manager.merge(request.toModel());
		
		return categoria.toString();
	}
}
