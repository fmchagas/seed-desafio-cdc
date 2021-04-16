package br.com.fmchagas.desafiocdc.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/compras")
//total carga: 6
public class CompraController {
	@PersistenceContext
	private EntityManager manager;
	
	//1
	private UfPertenceAPaisValidator estadoPertenceAPaisValidator;
	//1
	private PaisTemUfValidator paisTemUfValidator;
	//1
	private CupomExistenteEComDataValidaValidator cupomExistenteEComDataValidaValidator;
	
	public CompraController(UfPertenceAPaisValidator estadoPertenceAPaisValidator, PaisTemUfValidator paisTemUfValidator,
			CupomExistenteEComDataValidaValidator cupomExistenteEComDataValidaValidator) {
		this.estadoPertenceAPaisValidator = estadoPertenceAPaisValidator;
		this.paisTemUfValidator = paisTemUfValidator;
		this.cupomExistenteEComDataValidaValidator = cupomExistenteEComDataValidaValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(estadoPertenceAPaisValidator, paisTemUfValidator, cupomExistenteEComDataValidaValidator);
	}
	
	@PostMapping
	@Transactional
	//1
	public String novo(@Valid @RequestBody NovaCompraRequest request) {
		//1
		final Compra novaCompra = request.toModel(manager);
		//final Compra compraSalva = manager.merge(novaCompra);
		
		//final CompraResponse compraResponse = new CompraResponse(compraSalva);
		
		return novaCompra.toString();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CompraResponse> listaPorId(@PathVariable Long id){
		final Compra possivelCompra = manager.find(Compra.class, id);
		
		if(possivelCompra == null)
			return ResponseEntity.notFound().build();
		
		//1
		final CompraResponse compraResponse = new CompraResponse(possivelCompra);
		
		return ResponseEntity.ok(compraResponse);
	}
}
