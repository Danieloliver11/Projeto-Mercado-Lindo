package com.mercadolindo.repositories.specification;

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
import com.mercadolindo.model.filter.ProdutoFiltroVO;

public class ProdutoSpecification implements Specification<ProdutoEntity> {

	private static final long serialVersionUID = -9112179560601786827L;

	private transient ProdutoFiltroVO filtros;

	public ProdutoSpecification(ProdutoFiltroVO filtros) {
		this.filtros = filtros;
	}

	@Override
	public Predicate toPredicate(Root<ProdutoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> condicoes = new ArrayList<>();

		query.orderBy(criteriaBuilder.asc(root.get("id")));

		if (filtros.incluiNome()) {
			Predicate likeNome = criteriaBuilder.like(root.get("nome"), filtros.getNome() + "%");
			condicoes.add(likeNome);
		}

		if (filtros.getValorMinimo() != null && filtros.getValorMaximo() != null && filtros.getValorMinimo().compareTo(filtros.getValorMaximo()) < 0) {
			Predicate betweenMinAndMax = criteriaBuilder.between(root.get("preco"), filtros.getValorMinimo(), filtros.getValorMaximo());
			condicoes.add(betweenMinAndMax);
		}

		if (filtros.isFreteGratis()) {
			Predicate frete = criteriaBuilder.equal(root.get("freteGratis"), filtros.isFreteGratis());
			condicoes.add(frete);
		}

		if (filtros.incluiIdCategoria()) {
			Join<ProdutoEntity, CategoriaEntity> joinProdutoCategoria = root.join("categorias", JoinType.INNER);
			Predicate categoria = criteriaBuilder.equal(joinProdutoCategoria.get("id"), filtros.getIdCategoria());
			condicoes.add(categoria);
		}

		return criteriaBuilder.and(condicoes.toArray(new Predicate[0]));
	}

}
