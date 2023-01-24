package com.mercadolindo.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mercadolindo.model.ProdutoVO;

@SpringBootTest
class ProdutoServiceTest {

	@Autowired
	ProdutoService produtoService;
	
	
	@Test
	void cadastrarProduto() {	
		
		ProdutoVO vo = gerarProduto();
		
		ProdutoVO cadastro = produtoService.cadastrar(vo);
		assertNotNull(cadastro.getId());
	}
	
	@Test
	void pesquisarProduto() {
		
		long idUf = 35;
		
		Pageable page = Pageable.ofSize(1);
		
		ProdutoVO vo = gerarProduto();

		Page<ProdutoVO> produtos = produtoService.pesquisarProdutoPorFiltro
				(vo.getNome() , vo.getPreco() , new BigDecimal("111") , Boolean.TRUE, idUf, page) ;

		assertFalse(produtos.isEmpty());
	}

	private ProdutoVO gerarProduto() {
		ProdutoVO vo = new ProdutoVO();
		vo.setQuantidade(100);
		vo.setPreco(new BigDecimal("110"));
		vo.setDescricao("Melhor controle universal!");
		vo.setNome("Controle remoto");
		return vo;
	}
	

}
