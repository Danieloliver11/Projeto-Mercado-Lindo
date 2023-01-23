package com.mercadolindo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.entity.factory.ProdutoEntityFactory;
import com.mercadolindo.model.ProdutoCadastroVO;
import com.mercadolindo.model.factory.ProdutoVOFactory;
import com.mercadolindo.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public ProdutoCadastroVO cadastrar(ProdutoCadastroVO produto) {
			
		ProdutoEntity entity = ProdutoEntityFactory.toEntity(produto);
		
		produtoRepository.save(entity);
		
		return ProdutoVOFactory.toVO(entity);
	}
	
}
