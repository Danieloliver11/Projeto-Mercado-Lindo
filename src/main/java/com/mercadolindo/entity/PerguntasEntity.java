package com.mercadolindo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PERGUNTAS")
public class PerguntasEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TEXTO")
	private String texto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUTO", nullable = false)
	private ProdutoEntity produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	private PessoaEntity pessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RESPOSTA_PERGUNTA")
	private RespostaPerguntaEntity respostaPergunta;

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

	public RespostaPerguntaEntity getRespostaPergunta() {
		return respostaPergunta;
	}

	public void setRespostaPergunta(RespostaPerguntaEntity respostaPergunta) {
		this.respostaPergunta = respostaPergunta;
	}
	
	
	
}
