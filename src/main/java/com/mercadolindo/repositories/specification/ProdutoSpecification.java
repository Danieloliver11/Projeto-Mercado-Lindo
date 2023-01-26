package com.mercadolindo.repositories.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;

public class ProdutoSpecification implements Specification<ProdutoEntity> {

	private static final long serialVersionUID = -9112179560601786827L;
	
	private transient String nome;
	private transient BigDecimal valorMinimo;
	private transient BigDecimal valorMaximo;
	private transient boolean freteGratis;
	
	//TODO: FINALIZAR ESTE FLUXO DE UF
	private transient Long idUF;
	private transient Long idCategoria;

	public ProdutoSpecification(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo, boolean freteGratis,
			Long idUF ,Long idCategoria) {
		this.nome = nome;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.freteGratis = freteGratis;
		this.idUF = idUF;
		this.idCategoria = idCategoria;
	}


	@Override
	public Predicate toPredicate(Root<ProdutoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> condicoes = new ArrayList<>();
		
		query.orderBy(criteriaBuilder.asc(root.get("id")));
		
		if(nome != null && !nome.isBlank()) {
			Predicate likeNome = criteriaBuilder.like(root.get("nome"), nome + "%");
			condicoes.add(likeNome);
		}
		
		if(valorMinimo != null && valorMaximo != null && valorMinimo.compareTo(valorMaximo) < 0) {
			Predicate betweenMinAndMax = criteriaBuilder.between(root.get("preco"), valorMinimo, valorMaximo);
			condicoes.add(betweenMinAndMax);
		}
		
		if(freteGratis) {
			Predicate frete = criteriaBuilder.equal(root.get("freteGratis"), freteGratis);
			condicoes.add(frete);
		}

		if(idUF != null) {
//			Predicate uf = criteriaBuilder.equal(root, root);
//			condicoes.add(uf);
		}
		
		if(idCategoria != null) {
			Join<ProdutoEntity, CategoriaEntity> joinProdutoCategoria = root.join("categorias" , JoinType.INNER);
			Predicate categoria = criteriaBuilder.equal(joinProdutoCategoria.get("id"), idCategoria);
			condicoes.add(categoria);
		}
		
		return criteriaBuilder.and(condicoes.toArray(new Predicate[0]));
	}

}
