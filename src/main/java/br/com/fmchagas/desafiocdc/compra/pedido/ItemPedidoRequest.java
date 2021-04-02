package br.com.fmchagas.desafiocdc.compra.pedido;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fmchagas.desafiocdc.livro.Livro;
import br.com.fmchagas.desafiocdc.validation.ExistsId;

public class ItemPedidoRequest {
	
	@NotNull @ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long  livroId;
	
	@Positive
	private Integer quantidade;
	
	public ItemPedidoRequest(@NotNull Long livroId, @Positive Integer quantidade) {
		this.livroId = livroId;
		this.quantidade = quantidade;
	}
	
	public Long getLivroId() {
		return livroId;
	}

	@Override
	public String toString() {
		return "ItemPedidoRequest [livroId=" + livroId + ", quantidade=" + quantidade + "]";
	}
}
