package com.mercadolindo.model.factory;

import org.springframework.data.domain.Page;

import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.ProdutoVO;

public class ProdutoVOFactory{

	ProdutoVOFactory(){}
	
	public static ProdutoVO toVO(ProdutoEntity entity) {
		ProdutoVO vo = new ProdutoVO();
		vo.setId(entity.getId());
		vo.setDescricao(entity.getDescricao());
		vo.setPreco(entity.getPreco());
		vo.setNome(entity.getNome());
		vo.setQuantidade(entity.getQuantidade());
		return vo;
	}

	public static Page<ProdutoVO> pageEntityToPageVO(Page<ProdutoEntity> produtos) {
		return produtos.map(ProdutoVOFactory::toVO);
	}
	
}
