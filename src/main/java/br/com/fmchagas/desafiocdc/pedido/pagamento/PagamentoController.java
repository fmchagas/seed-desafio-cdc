package br.com.fmchagas.desafiocdc.pedido.pagamento;

import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pagamentos")
//total carga: 3
public class PagamentoController {
	//1
	private VerificaDocumentoCpfOuCnpjValidator verificaDocumentoCpfOuCnpjValidator;
	
	//1
	private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;
	
	public PagamentoController(VerificaDocumentoCpfOuCnpjValidator verificaDocumentoCpfOuCnpjValidator,
			EstadoPertenceAPaisValidator estadoPertenceAPaisValidator) {
		this.verificaDocumentoCpfOuCnpjValidator = verificaDocumentoCpfOuCnpjValidator;
		this.estadoPertenceAPaisValidator = estadoPertenceAPaisValidator;
	}
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(verificaDocumentoCpfOuCnpjValidator, estadoPertenceAPaisValidator);
	}
	
	@PostMapping
	//1
	public String novo(@Valid @RequestBody PagamentoRequest request) {

		return request.toString();
	}
}
