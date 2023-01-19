package com.mercadolindo.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mercadolindo.entity.PedidoEntity;
import com.mercadolindo.entity.ProdutoEntity;

@Embeddable
public class ItemPedidoPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "ID_PEDIDO")
	private PedidoEntity pedido;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private ProdutoEntity produto;
   
	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}
	
	

}
