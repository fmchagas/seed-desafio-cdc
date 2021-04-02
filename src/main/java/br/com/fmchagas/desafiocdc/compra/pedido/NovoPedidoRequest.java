package br.com.fmchagas.desafiocdc.compra.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class NovoPedidoRequest {
	@Positive
	@NotNull
	private BigDecimal total;
	
	@Size(min = 1)
	@Valid
	private List<ItemPedidoRequest> itens = new ArrayList<>();
	
	public NovoPedidoRequest(@Positive @NotNull BigDecimal total, 
			@Size(min = 1) @Valid List<ItemPedidoRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}
	
	public List<ItemPedidoRequest> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "NovoPedidoRequest [total=" + total + ", itens=" + itens + "]";
	}
}
