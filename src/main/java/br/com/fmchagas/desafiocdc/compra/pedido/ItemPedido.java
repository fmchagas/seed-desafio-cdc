package br.com.fmchagas.desafiocdc.compra.pedido;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.fmchagas.desafiocdc.livro.Livro;

@Embeddable
public class ItemPedido {
	
	@ManyToOne
	private @NotNull Livro livro;
	private @Positive Integer quantidade;
	private @Positive BigDecimal precoCompra;
	
	@Deprecated
	public ItemPedido() {}
	
	public ItemPedido(@NotNull Livro livro, @Positive Integer quantidade) {
		this.livro = livro;
		this.quantidade = quantidade;
		this.precoCompra = livro.getPreco();
	}
	
	public BigDecimal total() {
		return precoCompra.multiply(new BigDecimal(quantidade));
	}

	@Override
	public String toString() {
		return "ItemPedido [livro=" + livro + ", quantidade=" + quantidade + ", precoCompra=" + precoCompra + "]";
	}
}
