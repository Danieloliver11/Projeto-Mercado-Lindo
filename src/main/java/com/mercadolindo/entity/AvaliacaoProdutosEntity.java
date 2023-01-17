package com.mercadolindo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_AVALIACAO_PRODUTOS")
public class AvaliacaoProdutosEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TEXTO", nullable = false)
	private String texto;
	
	@Column(name = "NOTA")
	private short nota;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private ProdutoEntity produto;
	
	@ManyToOne
	@JoinColumn(name = "ID_PESSOA")
	private PessoaEntity pessoa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public short getNota() {
		return nota;
	}

	public void setNota(short nota) {
		this.nota = nota;
	}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

}
