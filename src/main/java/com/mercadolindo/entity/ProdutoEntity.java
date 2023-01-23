package com.mercadolindo.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

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
	
	@Column(name = "NOME", nullable = false , length = 150)
	private String nome;
	
	@Column(name = "PRECO", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;
	
	@Column(name = "DESCRICAO" , length = 150)
	private String descricao;
	
	@Column(name = "FLAG_FRETE_GRATIS" , nullable = false)
	private boolean freteGratis;
	
	@Lob
	@ElementCollection
	@CollectionTable(name = "TB_IMAGEM", joinColumns = @JoinColumn(name ="ID_PRODUTO"))
	@Column(name = "IMAGEM")
	private List<byte[]> imagens;
    
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
		
	public boolean isFreteGratis() {
		return freteGratis;
	}

	public void setFreteGratis(boolean freteGratis) {
		this.freteGratis = freteGratis;
	}
	
	public List<byte[]> getImagens() {
		return imagens;
	}

	public void setImagens(List<byte[]> imagens) {
		this.imagens = imagens;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoEntity other = (ProdutoEntity) obj;
		return Objects.equals(id, other.id);
	}

}
