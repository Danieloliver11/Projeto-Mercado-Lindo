package com.mercadolindo.model.factory;

import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.ProdutoCadastroVO;

public class ProdutoVOFactory{

	ProdutoVOFactory(){}
	
	public static ProdutoCadastroVO toVO(ProdutoEntity entity) {
		ProdutoCadastroVO vo = new ProdutoCadastroVO();
		vo.setId(entity.getId());
		vo.setDescricao(entity.getDescricao());
		vo.setPreco(entity.getPreco());
		vo.setNome(entity.getNome());
		vo.setQuantidade(entity.getQuantidade());
		return vo;
	}
	
}
