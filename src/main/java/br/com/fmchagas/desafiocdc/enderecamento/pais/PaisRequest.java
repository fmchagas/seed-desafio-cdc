package br.com.fmchagas.desafiocdc.enderecamento.pais;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.fmchagas.desafiocdc.validation.UniqueValue;
//Carga total: 1
public class PaisRequest {
	
	@NotBlank @UniqueValue(domainClass = Pais.class, fieldName = "nome")
	private String nome;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES) //corrige erro jackson, que não consegue serializar construtor com 1 argumento
	public PaisRequest(@NotEmpty String nome) {
		super();
		this.nome = nome;
	}
	
	//1
	public Pais toModel() {
		return new Pais(nome);
	}
}
