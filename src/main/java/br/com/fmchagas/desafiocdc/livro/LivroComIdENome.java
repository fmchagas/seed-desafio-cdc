package br.com.fmchagas.desafiocdc.livro;

import java.util.List;
import java.util.stream.Collectors;

//Total carga com f(x): 3
//Total carga com foreach: 1
public class LivroComIdENome {
	private Long id;
	private String nome;
	
	public LivroComIdENome(Livro livro) {
		id = livro.getId();
		nome = livro.getTitulo();
	}
	
	
	//1
	public static List<LivroComIdENome> converter(List<Livro> livros) {
		//2
		return livros.stream()
				.map(LivroComIdENome::new)
				.collect(Collectors.toList());
		
		/*List<LivroComIdENome> livrosConvertidos = new ArrayList<>();
		
		for (Livro livro : livros) {
			livrosConvertidos.add(new LivroComIdENome(livro));
		}
		
		return livrosConvertidos;*/
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
