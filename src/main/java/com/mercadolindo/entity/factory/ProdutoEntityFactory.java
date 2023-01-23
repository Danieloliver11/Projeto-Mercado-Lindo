package com.mercadolindo.entity.factory;

import java.math.BigDecimal;

import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.ProdutoVO;

public class ProdutoEntityFactory {

	ProdutoEntityFactory(){}
	
	public static ProdutoEntity toEntity(ProdutoVO vo) {
		
		ProdutoEntity entity = new ProdutoEntity();
		entity.setDescricao(vo.getDescricao());
		entity.setQuantidade(vo.getQuantidade());
		entity.setNome(vo.getNome());
		entity.setPreco(vo.getPreco());
		entity.setFreteGratis(entity.getPreco().compareTo(new BigDecimal("100")) >= 0);
		return entity;
	}

	
}
