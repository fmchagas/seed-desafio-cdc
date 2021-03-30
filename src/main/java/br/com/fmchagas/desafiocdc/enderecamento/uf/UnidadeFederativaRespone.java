package br.com.fmchagas.desafiocdc.enderecamento.uf;

import br.com.fmchagas.desafiocdc.enderecamento.pais.Pais;

public class UnidadeFederativaRespone {
	//O que acontece se add construtor antes de atributos - 
	public UnidadeFederativaRespone(UnidadeFederativa ufSalva) {
		this.id = ufSalva.getId();
	    this.nome = ufSalva.getNome();
	    this.pais = ufSalva.getPais();
	}
	
	private Long id;
	private String nome;
	private Pais pais;
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Pais getPais() {
		return pais;
	}
}
