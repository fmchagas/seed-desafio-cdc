package br.com.fmchagas.desafiocdc.livro.detalhe;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.fmchagas.desafiocdc.livro.Livro;

//Carga total: 1
public class LivroDetalheResponse {
	//private String linkCapa;
	private String titulo;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private Integer numeroPagina;
	private String isbn;
	private String dataPublicacao;
	private String autor;
	private String autorDescricao;
	
	
	//1
	public LivroDetalheResponse(Livro livro) {
		titulo = livro.getTitulo();
		resumo = livro.getResumo();
		sumario = livro.getSumario();
		numeroPagina = livro.getNumeroPagina();
		isbn = livro.getIsbn();
		dataPublicacao = livro.getDataPublicacao()
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		autor = livro.getAutor().getNome();
		autorDescricao = livro.getAutor().getDescricao();
		preco = livro.getPreco();
	}

	public String getTitulo() {
		return titulo;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDataPublicacao() {
		return dataPublicacao;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public String getAutorDescricao() {
		return autorDescricao;
	}
}
