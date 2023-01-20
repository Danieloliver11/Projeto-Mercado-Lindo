package com.mercadolindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mercadolindo.enums.StatusPedidoEnum;

@Entity
@Table(name = "TB_PEDIDO")
public class PedidoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DATA_PEDIDO")
	private LocalDate dataPedido;
	
	@Column(name = "OBSERVACAO")
	private String observacao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS_PEDIDO")
	private StatusPedidoEnum statusPedidoEnum;
	
	@Column(name = "DESCONTO")
	private BigDecimal desconto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	private PessoaEntity pessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PAGAMENTO", nullable = false)
	private PagamentoEntity pagamento;
	
	@OneToMany(mappedBy = "id.pedido")
	private List<ItemPedidoEntity> itemPedidoEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusPedidoEnum getStatusPedidoEnum() {
		return statusPedidoEnum;
	}

	public void setStatusPedidoEnum(StatusPedidoEnum statusPedidoEnum) {
		this.statusPedidoEnum = statusPedidoEnum;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemPedidoEntity> getItemPedidoEntity() {
		return itemPedidoEntity;
	}

	public void setItemPedidoEntity(List<ItemPedidoEntity> itemPedidoEntity) {
		this.itemPedidoEntity = itemPedidoEntity;
	}

	public PagamentoEntity getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoEntity pagamento) {
		this.pagamento = pagamento;
	}
	
	
	
	
	
	

}
