package com.mercadolindo.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolindo.model.ProdutoVO;
import com.mercadolindo.repositories.CategoriaRepository;
import com.mercadolindo.service.ProdutoService;

@RestController
@CrossOrigin(allowedHeaders = "*", maxAge = 1600)
@RequestMapping("/produtos")
class ProdutoController {

	final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService , CategoriaRepository categoriaRepository) {
		this.produtoService = produtoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoVO cadastrar(@Valid @RequestBody ProdutoVO produto) {
		return produtoService.cadastrar(produto);
	}
	
	@PutMapping
	public ProdutoVO atualizar(@Valid @RequestBody ProdutoVO produto) {
		return produtoService.atualizar(produto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/remover-produto/{id}")
	public void removerProduto(Long id) {
	     produtoService.removerProduto(id);
	}
	
	@GetMapping("/filtros")
	public Page<ProdutoVO> pesquisarProdutoPorFiltro(@RequestParam(required = false) String nome,
			@RequestParam(required = false) BigDecimal valorMinimo,
			@RequestParam(required = false) BigDecimal valorMaximo, @RequestParam(required = false) boolean freteGratis,
			@RequestParam(required = false) Long idUF ,
			@RequestParam(required = false) Long idCategoria , Pageable pageRequest) {
		return produtoService.pesquisarProdutoPorFiltro(nome, valorMinimo, valorMaximo, freteGratis, idUF, idCategoria,pageRequest);
	}
	
}
