package com.mercadolindo.model.factory;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.CategoriaVO;
import com.mercadolindo.model.ProdutoVO;

public class ProdutoVOFactory{

	ProdutoVOFactory(){}
	
	public static ProdutoVO toVO(ProdutoEntity entity) {
		if (entity != null) {
			ProdutoVO vo = new ProdutoVO();
			vo.setId(entity.getId());
			vo.setDescricao(entity.getDescricao());
			vo.setPreco(entity.getPreco());
			vo.setNome(entity.getNome());
			vo.setQuantidade(entity.getQuantidade());
			vo.setFreteGratis(entity.isFreteGratis());
			vo.setCategorias(popularCategoria(entity.getCategorias()));
			return vo;
		} else
			return null;
	}

	public static CategoriaVO converterCategoriaParaVO(CategoriaEntity categoria) {
		if(categoria != null) {
		CategoriaVO vo = new CategoriaVO();
		vo.setId(categoria.getId());
		vo.setNome(categoria.getNome());
		return vo;
	} else
		return null;
	}
	
	private static List<CategoriaVO> popularCategoria(List<CategoriaEntity> categorias) {
		return categorias.stream().map(ProdutoVOFactory::converterCategoriaParaVO).toList();
	}

	public static Page<ProdutoVO> pageEntityToPageVO(Page<ProdutoEntity> produtos) {
		return produtos.map(ProdutoVOFactory::toVO);
	}

}
