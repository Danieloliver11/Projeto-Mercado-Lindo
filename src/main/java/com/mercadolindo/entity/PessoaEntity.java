package com.mercadolindo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.mercadolindo.enums.SexoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "TB_PESSOA", uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class PessoaEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME", nullable = false,length = 50)
	private String nome;
	
	@CPF
	@Column(name = "CPF", nullable = false ,length = 14)
	private String cpf;
	
	
	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SEXO", nullable = false)
	private SexoEnum sexo;
	
	@Email
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@OneToOne
	@JoinColumn(name = "USUARIO")
	private UsuarioEntity usuario;
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<EnderecoEntity> enderecos;
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<PedidoEntity> pedidos;
	
	@OneToMany(mappedBy = "pessoa")
	private List<PerguntasEntity> perguntas; //N:N
	
	@OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL)
	private List<AvaliacaoProdutosEntity> avaliacaoProduto; //n:n

	public PessoaEntity() {
		super();
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}


	public List<EnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public List<PerguntasEntity> getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(List<PerguntasEntity> perguntas) {
		this.perguntas = perguntas;
	}

	public List<AvaliacaoProdutosEntity> getAvaliacaoProduto() {
		return avaliacaoProduto;
	}

	public void setAvaliacaoProduto(List<AvaliacaoProdutosEntity> avaliacaoProduto) {
		this.avaliacaoProduto = avaliacaoProduto;
	}

	public List<PedidoEntity> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
	
	
	
	

}
