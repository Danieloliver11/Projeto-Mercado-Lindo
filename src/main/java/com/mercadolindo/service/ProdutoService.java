package com.mercadolindo.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.entity.factory.ProdutoEntityFactory;
import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.model.factory.ProdutoVOFactory;
import com.mercadolindo.repositories.ProdutoRepository;
import com.mercadolindo.repositories.specification.ProdutoSpecification;

@Service
public class ProdutoService {

	final ProdutoRepository produtoRepository;

	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Transactional
	public ProdutoVO cadastrar(ProdutoVO produto) {
			
		ProdutoEntity entity = ProdutoEntityFactory.toEntity(produto);
		
		produtoRepository.save(entity);
		
		return ProdutoVOFactory.toVO(entity);
	}

	public Page<ProdutoVO> pesquisarProdutoPorFiltro(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo,
			boolean freteGratis, Long idUF, Pageable pageRequest) {
		
		Page<ProdutoEntity> produtos = produtoRepository.findAll(new ProdutoSpecification(nome , valorMinimo , valorMaximo , freteGratis, idUF) , pageRequest);
		
		return ProdutoVOFactory.pageEntityToPageVO(produtos);
	}
	
}
