package br.com.fmchagas.desafiocdc.pedido;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.enderecamento.uf.UnidadeFederativa;
import br.com.fmchagas.desafiocdc.validation.ExistsId;

public class PedidoRequest {
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
	
	@ExistsId(domainClass = UnidadeFederativa.class, fieldName = "id")
	private Long ufId;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String telefone;

	public PedidoRequest(@Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long paisId, Long ufId, @NotBlank String cep, @NotBlank String telefone) {
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
	}

	public boolean documentoValido() {
		Assert.hasLength(documento, "ops, não podemos validar o documento se ele não tiver preenchido");
		
		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
		
		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
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
		return "PagamentoRequest [email=" + email + ", nome=" + nome + ", sobrenome=" + sobrenome + ", documento="
				+ documento + ", endereco=" + endereco + ", complemento=" + complemento + ", cidade=" + cidade
				+ ", paisId=" + paisId + ", ufId=" + ufId + ", cep=" + cep + ", telefone=" + telefone + "]";
	}
}
