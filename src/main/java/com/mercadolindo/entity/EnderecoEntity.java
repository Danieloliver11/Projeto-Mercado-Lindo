package com.mercadolindo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mercadolindo.enums.SimNaoEnum;

@Entity
@Table(name = "TB_ENDERECO")
public class EnderecoEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "BAIRRO", nullable = false)
	private String bairro;
	
	@Column(name = "RUA", nullable = false)
	private String rua;
	
	@Column(name = "NUMERO", nullable = false)
	private String numero;
	
	@Column(name = "CEP", nullable = false)
	private String cep;
	
	@Column(name = "FLAGE_ENDERECO_PRINCIPAL", nullable = false)
	private SimNaoEnum flagEnderecoPrincipal;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PESSOA", nullable = false,foreignKey = @ForeignKey(name ="FK_ENDERECO_PESSOA"))
	private PessoaEntity pessoa;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CIDADE",foreignKey = @ForeignKey(name ="FK_ENDERECO_CIDADE"))
	private CidadeEntity cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public CidadeEntity getCidade() {
		return cidade;
	}

	public void setCidade(CidadeEntity cidade) {
		this.cidade = cidade;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public SimNaoEnum getFlagEnderecoPrincipal() {
		return flagEnderecoPrincipal;
	}

	public void setFlagEnderecoPrincipal(SimNaoEnum flagEnderecoPrincipal) {
		this.flagEnderecoPrincipal = flagEnderecoPrincipal;
	}
	
	
	
	

}
