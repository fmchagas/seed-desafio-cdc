package br.com.fmchagas.desafiocdc.enderecamento.pais;
//totoal: 1
public class PaisResposne {
	
	private Long id;
	private String nome;
	
	//1
	public PaisResposne(Pais pais) {
		this.id = pais.getId();
		this.nome = pais.getNome();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
