package br.com.fmchagas.desafiocdc.compra;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.compra.pedido.NovoPedidoRequest;
import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa;
import br.com.fmchagas.desafiocdc.validation.ExistsId;

//total carga: 4
public class NovaCompraRequest {
	@Email @NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@NotBlank
	private String documento;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String cidade;
	
	@NotNull @ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long paisId;
	//1
	@ExistsId(domainClass = UnidadeFederativa.class, fieldName = "id")
	private Long ufId;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String telefone;
	
	//1
	@Valid @NotNull
	private NovoPedidoRequest pedido;

	public NovaCompraRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long ufId, @NotBlank String cep,
			@NotBlank String telefone, @Valid @NotNull NovoPedidoRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.paisId = paisId;
		this.ufId = ufId;
		this.cep = cep;
		this.telefone = telefone;
		this.pedido = pedido;
	}

	public boolean documentoValido() {
		Assert.hasLength(documento, "ops, não podemos validar o documento se ele não tiver preenchido");
		
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}
	
	//1
	public Compra toModel(EntityManager manager) {
		//1
		@NotNull Pais pais = manager.find(Pais.class, paisId);
		
		Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, telefone, cep, pais);
		
		//1
		if(ufId != null) {
			compra.setUf(manager.find(UnidadeFederativa.class, ufId));
		}
		
		return compra;
	}
	
	public boolean temUf() {
		return ufId != null;
	}
	
	public NovoPedidoRequest getCarrinho() {
		return pedido;
	}

	public Long getPaisId() {
		return paisId;
	}
	
	public String getDocumento() {
		return this.documento;
	}
	
	public Long getUfId() {
		return ufId;
	}

	@Override
	public String toString() {
		return "NovaCompraRequest [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", paisId=" + paisId + ", ufId=" + ufId + ", cep=" + cep + ", telefone=" + telefone + ", pedido="
				+ pedido + "]";
	}
}
