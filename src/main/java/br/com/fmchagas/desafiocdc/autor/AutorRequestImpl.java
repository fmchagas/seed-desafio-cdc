package br.com.fmchagas.desafiocdc.autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.fmchagas.desafiocdc.validation.UniqueValue;

//2
public class AutorRequestImpl implements AutorRequest{
	
	@NotEmpty private String nome;
	
	@UniqueValue(domainClass = Autor.class, fieldName = "email")
	@Email @NotEmpty private String email;
	
	@NotEmpty @Size(max = 400) private String descricao;
	
	

	public AutorRequestImpl(@NotEmpty String nome, @Email @NotEmpty String email,
			@NotEmpty @Size(max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	//1
	public Autor toModel() {
		return new Autor(nome, email, descricao);
	}
	
	public String getEmail() {
		return email;
	}
}
