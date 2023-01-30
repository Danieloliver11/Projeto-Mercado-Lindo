package com.mercadolindo.entity.factory;

import java.util.List;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.model.CategoriaVO;

public class CategoriaEntityFactory {
	
	CategoriaEntityFactory(){}
	
	public static CategoriaEntity converterParaEntity(CategoriaVO categoria) {
		CategoriaEntity entity = new  CategoriaEntity();
		entity.setId(categoria.getId());
		return entity;
	}
	
	public static List<CategoriaEntity> converterListParaEntity(List<CategoriaVO> categorias) {
		return categorias.stream().map(categoria -> converterParaEntity(categoria)).toList();
	}


}
