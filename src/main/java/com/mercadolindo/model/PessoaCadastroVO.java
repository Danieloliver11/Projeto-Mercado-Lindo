package com.mercadolindo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import com.mercadolindo.enums.FuncaoNome;
import com.mercadolindo.enums.SexoEnum;

public class PessoaCadastroVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private SexoEnum sexo;
	
	private String email;
	
	private String nomeUsuario;
	
	private String senha;
	
	private ZonedDateTime ultimoAcesso;
	
	private FuncaoNome flagAdm;
	
	private String token;
	
	

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

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public ZonedDateTime getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(ZonedDateTime ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public FuncaoNome getFlagAdm() {
		return flagAdm;
	}

	public void setFlagAdm(FuncaoNome flagAdm) {
		this.flagAdm = flagAdm;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
