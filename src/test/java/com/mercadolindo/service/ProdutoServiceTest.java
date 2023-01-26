package com.mercadolindo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.repositories.ProdutoRepository;

@TestInstance(Lifecycle.PER_CLASS)
class ProdutoServiceTest {

	final ProdutoVO vo = gerarProduto();
	
	@InjectMocks 
	ProdutoService produtoService;

	@Mock
	ProdutoRepository produtoRepository;
	
	@BeforeAll
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void cadastrarProduto() {	
		
		ProdutoVO cadastro = produtoService.cadastrar(vo);
		
		assertNotNull(cadastro.getNome());
	}

	@Test
	void nullPointerAoPesquisarProduto() {	
		
		assertThrows(NullPointerException.class, () -> produtoService.pesquisarProdutoPorFiltro(null, null, null, false, null, null, null));
		
	}

	
	private ProdutoVO gerarProduto() {
		ProdutoVO vo = new ProdutoVO();
		vo.setQuantidade(100);
		vo.setPreco(new BigDecimal("110"));
		vo.setDescricao("Melhor controle universal!");
		vo.setNome("Controle remoto");
		vo.setCategorias(new ArrayList<>());
		return vo;
	}

}
