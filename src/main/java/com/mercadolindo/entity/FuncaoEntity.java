package com.mercadolindo.entity;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;
import com.mercadolindo.enums.FuncaoNome;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_FUNCAO")
public class FuncaoEntity implements GrantedAuthority , Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "NOME", nullable = false , unique = true)
	private FuncaoNome funcaoNome;
	
	@Override
	public String getAuthority() {
		return this.getFuncaoNome().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FuncaoNome getFuncaoNome() {
		return funcaoNome;
	}
	
	public void setFuncaoNome(FuncaoNome funcaoNome) {
		this.funcaoNome = funcaoNome;
	}
}
