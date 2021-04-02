package br.com.fmchagas.desafiocdc.compra.pedido;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fmchagas.desafiocdc.livro.Livro;
import br.com.fmchagas.desafiocdc.validation.ExistsId;

public class NovoItemPedidoRequest {
	
	@NotNull @ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long  livroId;
	
	@Positive
	private Integer quantidade;
	
	public NovoItemPedidoRequest(@NotNull Long livroId, @Positive Integer quantidade) {
		this.livroId = livroId;
		this.quantidade = quantidade;
	}
	
	public Long getLivroId() {
		return livroId;
	}


	@Override
	public String toString() {
		return "NovoItemPedidoRequest [livroId=" + livroId + ", quantidade=" + quantidade + "]";
	}

	public ItemPedido toModel(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, livroId);
		return new ItemPedido(livro, quantidade);
	}
}
