package com.mercadolindo.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.mercadolindo.entity.CategoriaEntity;
import com.mercadolindo.entity.ProdutoEntity;
import com.mercadolindo.model.CategoriaVO;
import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.model.filter.ProdutoFiltroVO;
import com.mercadolindo.repositories.CategoriaRepository;
import com.mercadolindo.repositories.ProdutoRepository;
import com.mercadolindo.service.ProdutoService;

@TestInstance(Lifecycle.PER_CLASS)
class ProdutoServiceTest {

    private final ProdutoVO vo = gerarProduto();
	
    private static Random random = new Random();

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
	void pesquisarProdutoPorFiltro() {	
		
		ProdutoFiltroVO filtros = new ProdutoFiltroVO();
		filtros.setFreteGratis(Boolean.TRUE);
		
		List<ProdutoEntity> produtos = new ArrayList<>();
		produtos.add(getProduto(true));
		
		Page<ProdutoEntity> pagina = new PageImpl<>(produtos);
		Pageable pageRequest = Pageable.ofSize(1);

		when(produtoRepository.findAll(any(), any())).thenReturn(pagina);
		Page<ProdutoVO> page = produtoService.pesquisarProdutoPorFiltro(filtros , pageRequest);
		assertNotNull(page);

	}

	
	
	private ProdutoEntity getProduto(boolean comId) {
		ProdutoEntity produtoEntity = new ProdutoEntity();
		produtoEntity.setId(comId ? random.nextLong() : null);
		produtoEntity.setDescricao("Teste mock");
		produtoEntity.setQuantidade(10);
		produtoEntity.setPreco(new BigDecimal("101.00"));
		produtoEntity.setCategorias(getCategorias());
		return produtoEntity;
	}

	private List<CategoriaEntity> getCategorias() {
		CategoriaEntity categoria = new CategoriaEntity();
		categoria.setId(random.nextLong());
		categoria.setNome("Teste mock");
		
		return Arrays.asList(categoria);
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
