package com.mercadolindo.entity;

import static com.mercadolindo.utils.Constante.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "TB_USUARIO")
public class UsuarioEntity implements  UserDetails , Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME_USUARIO", nullable = false,length = 50)
	private String nomeUsuario;
	
	@Column(name = "ULTIMO_ACESSO", nullable = false)
	private ZonedDateTime ultimoAcesso;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "LOGIN", nullable = false) //
	private String login;
	
	@Column(name = "SENHA", nullable = false)
	private String senha;
	
	@OneToOne(mappedBy = "usuario")
	private PessoaEntity pessoa;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TB_USUARIOS_FUNCOES",
	joinColumns = @JoinColumn(name = "usuario_id") ,
	inverseJoinColumns = @JoinColumn(name = "funcao_id"))
	private List<FuncaoEntity> roles;
	
	public UsuarioEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public ZonedDateTime getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(ZonedDateTime ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	public List<FuncaoEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<FuncaoEntity> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	@PrePersist
	private void onCreate() {
		ultimoAcesso = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

	@PreUpdate
	private void onUpdate() {
		ultimoAcesso = ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList();
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.nomeUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
