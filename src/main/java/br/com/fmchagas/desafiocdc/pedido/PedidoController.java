package br.com.fmchagas.desafiocdc.pedido;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pedidos")
//total carga: 5
public class PedidoController {
	@PersistenceContext
	private EntityManager manager;
	
	//1
	private VerificaDocumentoCpfOuCnpjValidator verificaDocumentoCpfOuCnpjValidator;
	//1
	private UfPertenceAPaisValidator estadoPertenceAPaisValidator;
	//1
	private PaisTemUfValidator paisTemUfValidator;
	
	public PedidoController(VerificaDocumentoCpfOuCnpjValidator verificaDocumentoCpfOuCnpjValidator,
			UfPertenceAPaisValidator estadoPertenceAPaisValidator, PaisTemUfValidator paisTemUfValidator) {
		this.verificaDocumentoCpfOuCnpjValidator = verificaDocumentoCpfOuCnpjValidator;
		this.estadoPertenceAPaisValidator = estadoPertenceAPaisValidator;
		this.paisTemUfValidator = paisTemUfValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(verificaDocumentoCpfOuCnpjValidator, estadoPertenceAPaisValidator, paisTemUfValidator);
	}
	
	@PostMapping
	//1
	public String novo(@Valid @RequestBody PedidoRequest request) {
		Pedido novoPedido = request.toModel(manager);
		
		return request.toString();
	}
}
