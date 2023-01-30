package com.mercadolindo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.entity.factory.CategoriaEntityFactory;
import com.mercadolindo.entity.factory.ProdutoEntityFactory;
import com.mercadolindo.exception.NaoEncontradoException;
import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.model.factory.ProdutoVOFactory;
import com.mercadolindo.model.filter.ProdutoFiltroVO;
import com.mercadolindo.repositories.CategoriaRepository;
import com.mercadolindo.repositories.ProdutoRepository;
import com.mercadolindo.repositories.specification.ProdutoSpecification;
import com.mercadolindo.utils.VerificaDuplicadosUtils;

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
	    
		VerificaDuplicadosUtils.verificaDuplicado(produto.getCategorias());

	    entity.setCategorias(CategoriaEntityFactory.converterListParaEntity(produto.getCategorias()));
	    
	    produtoRepository.save(entity);
	    
		return ProdutoVOFactory.toVO(entity);
	}

	public Page<ProdutoVO> pesquisarProdutoPorFiltro(ProdutoFiltroVO filtros, Pageable pageRequest) {
		
		Page<ProdutoEntity> produtos = produtoRepository.findAll(new ProdutoSpecification(filtros) , pageRequest);
		
		return ProdutoVOFactory.pageEntityToPageVO(produtos);
	}
	
	@Transactional
	public ProdutoVO atualizar(ProdutoVO produto) {
		
		ProdutoEntity produtoBanco = recuperarProdutoPorId(produto.getId());
		
		ProdutoEntity produtoAlterado = produtoRepository.save(ProdutoEntityFactory.atualizar(produtoBanco , produto));
		
		List<CategoriaEntity> categoriasOriginais = produtoBanco.getCategorias();
		List<CategoriaEntity> categoriasAlteradas = CategoriaEntityFactory.converterListParaEntity(produto.getCategorias());
		
		if(!categoriasOriginais.isEmpty()) {
			removerCategorias(categoriasOriginais, categoriasAlteradas);
			categoriasAlteradas = getNovasCategorias(categoriasOriginais, categoriasAlteradas);
		}
		
		if(!categoriasAlteradas.isEmpty()) {
			categoriasAlteradas.forEach(categoria -> {
				CategoriaEntity categoriaEntity = categoriaRepository.findById(categoria.getId()).orElseThrow(
						() -> new NaoEncontradoException("Nenhuma categoria encontrada com id: "+ categoria.getId()));
				categoriasOriginais.add(categoriaEntity);
			});
		}
		
		produtoBanco.setCategorias(categoriasOriginais);
		
		return ProdutoVOFactory.toVO(produtoAlterado);
	}

	private List<CategoriaEntity> getNovasCategorias(List<CategoriaEntity> categoriasOriginais,
			List<CategoriaEntity> categoriasAlteradas) {
		return categoriasAlteradas.stream()
                .filter(alterada -> categoriasOriginais.stream()
                .filter(original -> alterada.getId().equals(original.getId())).findFirst().isEmpty())
                .collect(Collectors.toList());
	}

	private void removerCategorias(
			List<CategoriaEntity> categoriasOriginal,
			List<CategoriaEntity> categoriasAlteradas) {
		List<CategoriaEntity> removidos = categoriasOriginal.stream().filter(original -> 
		 categoriasAlteradas.stream().filter(
				 alterada -> original.getId().equals(alterada.getId())).findFirst().isEmpty()).toList();
		removidos.stream().forEach(categoria -> 
			categoriasOriginal.remove(categoria)
		);
	}

	@Transactional
	public void removerProduto(Long id) {
		ProdutoEntity produtoBanco = recuperarProdutoPorId(id);
		produtoRepository.deleteById(produtoBanco.getId());
	}

	
	private ProdutoEntity recuperarProdutoPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() 
				-> new NaoEncontradoException(String.format("Nenhum produto encontrado com id: %s" , id)));
	}

	public ProdutoVO pesquisarProdutoPorId(Long id) {
		
		ProdutoEntity produto = recuperarProdutoPorId(id);
		
		return ProdutoVOFactory.toVO(produto);
	}

	
}
