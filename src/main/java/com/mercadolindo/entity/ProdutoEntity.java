package com.mercadolindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUTO")
public class ProdutoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "PRECO", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	@Column(name = "DESCRICACAO")
	private String descricao;
	
//	@OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
//	private List<Imagem> imagens;
	
	@Lob
	@ElementCollection
	@CollectionTable(name = "TB_IMAGEM")
	@Column(name = "IMAGEM")
	private Collection<byte[]> imagens;
	
	@OneToMany(mappedBy = "id.produto")
	private List<ItemPedidoEntity> itemPedidos;
	
	@OneToMany(mappedBy = "produto")
	private List<PerguntasEntity> perguntas;  //N:N
	
	@OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
	private List<AvaliacaoProdutosEntity> avaliacaoProduto; //n:n

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public Collection<byte[]> getImagens() {
		return imagens;
	}

	public void setImagens(Collection<byte[]> imagens) {
		this.imagens = imagens;
	}

	public List<ItemPedidoEntity> getItemPedidoEntity() {
		return itemPedidos;
	}

	public void setItemPedidoEntity(List<ItemPedidoEntity> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public List<ItemPedidoEntity> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedidoEntity> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public List<PerguntasEntity> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<PerguntasEntity> perguntas) {
		this.perguntas = perguntas;
	}

	
	
	
	
	
	
	

}
