package com.mercadolindo.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.CategoriaVO;
import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.repositories.CategoriaRepository;
import com.mercadolindo.repositories.ProdutoRepository;
import com.mercadolindo.service.ProdutoService;

@TestInstance(Lifecycle.PER_CLASS)
class ProdutoServiceTest {

    private final ProdutoVO vo = gerarProduto();
	
	@InjectMocks 
	ProdutoService produtoService;

	@Mock
	ProdutoRepository produtoRepository;
	
	@Mock
	CategoriaRepository categoriaRepository;
	
	@BeforeAll
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void cadastrarProduto() {
		
		when(categoriaRepository.findById(1L)).thenReturn(Optional.of(new CategoriaEntity()));
		
		ProdutoVO cadastro = produtoService.cadastrar(vo);
		
		assertNotNull(cadastro.getNome());
	}
	
	@Test
	void atualizarProduto() {	
		vo.setId(1L);
		
		recuperarProdutoPorIdMock();
		
		produtoService.atualizar(vo);
		assertEquals(1L , vo.getId());
	}

	@Test
	void removerProduto() {
		recuperarProdutoPorIdMock();
		produtoService.removerProduto(1L);
	}

	@Test
	void nullPointerAoPesquisarProduto() {	
		
		assertThrows(NullPointerException.class, () -> produtoService.pesquisarProdutoPorFiltro(null, null, null, false, null, null, null));
		
	}

	private void recuperarProdutoPorIdMock() {
		when(produtoRepository.findById(1L)).thenReturn(Optional.of(new ProdutoEntity()));
	}
	
	private ProdutoVO gerarProduto() {
		ProdutoVO vo = new ProdutoVO();
		vo.setQuantidade(100);
		vo.setPreco(new BigDecimal("110"));
		vo.setDescricao("Melhor controle universal!");
		vo.setNome("Controle remoto");

		vo.setCategorias(gerarCategoria());
		return vo;
	}

	private List<CategoriaVO> gerarCategoria() {
		CategoriaVO categoria = new CategoriaVO();
		categoria.setId(1L);
		return Arrays.asList(categoria);
	}

}
