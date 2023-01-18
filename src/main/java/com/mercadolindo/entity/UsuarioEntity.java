package com.mercadolindo.entity;

import static com.mercadolindo.utils.Constante.AMERICA_SAO_PAULO;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

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
	
	@Column(name = "PERMISSAO", nullable = false)
	private String permissao;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "LOGIN", nullable = false)
	private String login;
	
	@Column(name = "SENHA", nullable = false)
	private Integer senha;
	
	@OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL)
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

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
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

	public Integer getSenha() {
		return senha;
	}

	public void setSenha(Integer senha) {
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
		return this.senha.toString();
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
