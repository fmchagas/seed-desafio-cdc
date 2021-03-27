package br.com.fmchagas.desafiocdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.fmchagas.desafiocdc.autor.Autor;
import br.com.fmchagas.desafiocdc.categoria.Categoria;
import br.com.fmchagas.desafiocdc.validation.ExistsId;
import br.com.fmchagas.desafiocdc.validation.UniqueValue;

//carga total: 3
public class LivroRequest {
	
	@UniqueValue(domainClass = Livro.class, fieldName = "titulo")
	private @NotEmpty String titulo;
	
	private @NotEmpty @Size(max = 500) String resumo;
	
	private String sumario;
	
	private @NotNull @Min(20) BigDecimal preco;
	
	private @NotNull @Min(100) Integer numeroPagina;
	
	@UniqueValue(domainClass = Livro.class, fieldName = "isbn")
	private @NotEmpty String isbn;
	
	//@JsonFormat(pattern = "dd/mm/yyyy") //shape = Shape.STRING, locale = "pt-BR", timezone = "Brazil/East"
	@Future
	private @NotNull LocalDate dataPublicacao;
	
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private @NotNull Long categoriaId;
	
	@ExistsId(domainClass = Autor.class, fieldName = "id")
	private @NotNull Long autorId;
	
	public LivroRequest(@NotEmpty String titulo, @NotEmpty @Size(max = 500) String resumo,
			String sumario, @NotNull @Min(value = 20) BigDecimal preco, 
			@NotNull @Min(value = 100) Integer numeroPagina, @NotEmpty String isbn, 
			@NotNull @Future LocalDate dataPublicacao, 
			@NotNull Long categoriaId,@NotNull Long autorId) {
				this.titulo = titulo;
				this.resumo = resumo;
				this.sumario = sumario;
				this.preco = preco;
				this.numeroPagina = numeroPagina;
				this.isbn = isbn;
				this.dataPublicacao = dataPublicacao;
				this.categoriaId = categoriaId;
				this.autorId = autorId;
	}
	//1
	public Livro toModel(EntityManager manager){
		//1
		@NotNull Autor autor = manager.find(Autor.class, autorId);
		
		//1
		@NotNull Categoria categoria = manager.find(Categoria.class, categoriaId);
		
		return new Livro(titulo,resumo,
				sumario, preco, 
				numeroPagina, isbn, 
				dataPublicacao, 
				autor, categoria);
	}
}
