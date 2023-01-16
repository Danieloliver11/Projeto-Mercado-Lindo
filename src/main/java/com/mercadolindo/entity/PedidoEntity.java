package com.mercadolindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.mercadolindo.enums.StatusPedidoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ESTADO")
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
	
	@ManyToOne
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	private PessoaEntity pessoa;
	
	@OneToMany(mappedBy = "id.pedido")
	private List<ItemPedidoEntity> itemPedidoEntity;
	

	
	

}
