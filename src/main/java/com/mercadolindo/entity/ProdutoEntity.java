package com.mercadolindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@Column(name = "PRECO", precision = 19, scale = 2, nullable = false)
	private BigDecimal preco;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	@Lob
	@Column(name = "DESCRICAO")
//	@Column(columnDefinition = "varchar (300) not null default 'DESCRICAO' ")
	private String descricao;
	
	@Lob
	@ElementCollection
	@CollectionTable(name = "TB_IMAGEM", joinColumns = @JoinColumn(name ="ID_PRODUTO"))
	@Column(name = "IMAGEM")
	private Collection<byte[]> imagens;
	
	@OneToMany(mappedBy = "id.produto")
	private List<ItemPedidoEntity> itemPedidos;
	//N:N
	@OneToMany(mappedBy = "produto")
	private List<PerguntasEntity> perguntas;  
	 //n:n
	@OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
	private List<AvaliacaoProdutosEntity> avaliacaoProduto;

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
