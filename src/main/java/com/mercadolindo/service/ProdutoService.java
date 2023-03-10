package com.mercadolindo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.entity.factory.ProdutoEntityFactory;
import com.mercadolindo.exception.NaoEncontradoException;
import com.mercadolindo.model.CategoriaVO;
import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.model.factory.ProdutoVOFactory;
import com.mercadolindo.repositories.CategoriaRepository;
import com.mercadolindo.repositories.ProdutoRepository;
import com.mercadolindo.repositories.specification.ProdutoSpecification;

@Service
public class ProdutoService {

	final ProdutoRepository produtoRepository;
	
	final CategoriaRepository categoriaRepository;

	public ProdutoService(ProdutoRepository produtoRepository , CategoriaRepository categoriaRepository) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional
	public ProdutoVO cadastrar(ProdutoVO produto) {
			
	    ProdutoEntity entity = ProdutoEntityFactory.toEntity(produto);
	    
	    List<CategoriaEntity> categorias = popularCategoria(produto.getCategorias());
		
	    entity.setCategorias(categorias);
	    
		return ProdutoVOFactory.toVO(entity);
	}

	public Page<ProdutoVO> pesquisarProdutoPorFiltro(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo,
			boolean freteGratis, Long idUF, Long idCategoria, Pageable pageRequest) {
		
		Page<ProdutoEntity> produtos = produtoRepository.findAll(new ProdutoSpecification(nome , valorMinimo , valorMaximo , freteGratis, idCategoria ,idUF) , pageRequest);
		
		return ProdutoVOFactory.pageEntityToPageVO(produtos);
	}
	
	private List<CategoriaEntity> popularCategoria(List<CategoriaVO> list) {
		List<CategoriaEntity> categorias = new ArrayList<>();
	    
		if(list != null) {
	    list.forEach(vo -> {
	    	CategoriaEntity categoria = categoriaRepository.findById(vo.getId()).orElseThrow(
	    			() -> new NaoEncontradoException("Nenhuma categoria encontrada com id: " + vo.getId()));	
	    	categorias.add(categoria);
	    });
		}
		
	    return categorias;
	    
	}

	
}
