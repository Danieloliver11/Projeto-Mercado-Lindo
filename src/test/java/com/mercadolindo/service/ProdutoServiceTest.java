package com.mercadolindo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mercadolindo.model.ProdutoCadastroVO;

@SpringBootTest
class ProdutoServiceTest {

	@Autowired
	ProdutoService produtoService;
	
	@Test
	void cadastrarProduto() {	
		
		ProdutoCadastroVO vo = new ProdutoCadastroVO();
		vo.setQuantidade(100);
		vo.setPreco(new BigDecimal("110"));
		vo.setDescricao("Melhor controle universal!");
		vo.setNome("Controle remoto");
		
		ProdutoCadastroVO cadastro = produtoService.cadastrar(vo);
		assertNotNull(cadastro.getId());
	}
	

}
