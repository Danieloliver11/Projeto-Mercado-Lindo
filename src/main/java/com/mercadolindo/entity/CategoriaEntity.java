package com.mercadolindo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_CATEGORIA",
        uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = { "nome" }) })
public class CategoriaEntity implements Serializable{
	
	private static final long serialVersionUID = -4390619495374697679L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(length = 100, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private List<ProdutoEntity> produtos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_pai_id",
            foreignKey = @ForeignKey(name = "fk_categoria_categoria_pai"))
    private CategoriaEntity categoriaPai;

    @OneToMany(mappedBy = "categoriaPai")
    private List<CategoriaEntity> categorias;
    
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

	public List<ProdutoEntity> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}
	
	public CategoriaEntity getCategoriaPai() {
		return categoriaPai;
	}
	
	public void setCategoriaPai(CategoriaEntity categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<CategoriaEntity> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<CategoriaEntity> categorias) {
		this.categorias = categorias;
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
		CategoriaEntity other = (CategoriaEntity) obj;
		return Objects.equals(id, other.id);
	}
    
	
    
}
