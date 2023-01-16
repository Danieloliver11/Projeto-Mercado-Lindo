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
@Table(name = "TB_PERGUNTAS")
public class PerguntasEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "TEXTO")
	private String texto;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO", nullable = false)
	private ProdutoEntity produto;
	
	@ManyToOne
	@JoinColumn(name = "ID_PESSOA", nullable = false)
	private PessoaEntity pessoa;
	
	@ManyToOne
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
