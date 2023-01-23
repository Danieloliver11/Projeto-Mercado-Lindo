package com.mercadolindo.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolindo.model.ProdutoCadastroVO;
import com.mercadolindo.service.ProdutoService;

@RestController
@CrossOrigin(allowedHeaders = "*" , maxAge = 1600)
@RequestMapping("/produtos")
class ProdutoController {

	final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	ProdutoCadastroVO cadastrar(@Valid @RequestBody ProdutoCadastroVO produto) {
		return produtoService.cadastrar(produto);
	}
	
	
}
