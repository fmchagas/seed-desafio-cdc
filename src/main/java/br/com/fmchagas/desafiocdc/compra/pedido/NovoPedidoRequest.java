package br.com.fmchagas.desafiocdc.compra.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import br.com.fmchagas.desafiocdc.compra.Compra;

public class NovoPedidoRequest {
	@Positive
	@NotNull
	private BigDecimal total;
	
	@Size(min = 1)
	@Valid
	private List<NovoItemPedidoRequest> itens = new ArrayList<>();
	
	public NovoPedidoRequest(@Positive @NotNull BigDecimal total, 
			@Size(min = 1) @Valid List<NovoItemPedidoRequest> itens) {
		this.total = total;
		this.itens = itens;
	}
	
	public List<NovoItemPedidoRequest> getItens() {
		return itens;
	}

	public Function<Compra, Pedido> toModel(EntityManager manager) {
		
		Set<ItemPedido> itensCalculados = itens.stream().map(item->item.toModel(manager)).collect(Collectors.toSet());
		
		//Lazy evaluation
		return compra -> {
			Pedido pedido = new Pedido(compra, itensCalculados);
			Assert.isTrue(pedido.totalIgual(total), "total enviado deve ser igual ao total real do itens");
			return pedido;
		};
	}
}
