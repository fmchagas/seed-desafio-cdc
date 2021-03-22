package br.com.fmchagas.desafiocdc.autor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Não pode ser vazio")
	private String nome;
	
	@Email @NotEmpty(message = "Não pode ser vazio")
	private String email;
	
	@NotEmpty(message = "Não pode ser vazio")
	@Size(max = 400, message = "Deve ter no maximo {max} caracteres")
	private String descricao;
	
	@NotNull
	@Column(name = "criado_em")
	private LocalDateTime criadoEm = LocalDateTime.now();
	
	
	public Autor(String nome, String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
