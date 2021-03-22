package br.com.fmchagas.desafiocdc.autor;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AutorForm {

	private Long id;
	
	@NotEmpty(message = "Não pode ser vazio")
	private String nome;
	
	@Email @NotEmpty(message = "Não pode ser vazio")
	private String email;
	
	@NotEmpty(message = "Não pode ser vazio")
	@Size(max = 400, message = "Deve ter no maximo {max} caracteres")
	private String descricao;
	
	private LocalDateTime criadoEm;


	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}
}
