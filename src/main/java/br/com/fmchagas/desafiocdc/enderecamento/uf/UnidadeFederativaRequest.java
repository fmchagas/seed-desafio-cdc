package br.com.fmchagas.desafiocdc.enderecamento.uf;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;
import br.com.fmchagas.desafiocdc.validation.ExistsId;
import br.com.fmchagas.desafiocdc.validation.UniqueValue;

//total : 2
public class UnidadeFederativaRequest {
	
	@NotBlank @UniqueValue(domainClass = UnidadeFederativa.class, fieldName = "nome")
	private String nome;
	
	@NotNull @ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long paisId;
	
	public UnidadeFederativaRequest(@NotBlank String nome, Long paisId) {
		super();
		this.nome = nome;
		this.paisId = paisId;
	}
	
	//1
	public UnidadeFederativa toModel(EntityManager manager) {
		//1
		@NotNull Pais pais = manager.find(Pais.class, paisId);
		
		return new UnidadeFederativa(nome, pais);
	}
}
