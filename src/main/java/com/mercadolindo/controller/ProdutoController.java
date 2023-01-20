package com.mercadolindo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercadolindo.service.ProdutoService;

@RestController
@CrossOrigin(allowedHeaders = "*" , maxAge = 1600)
@RequestMapping("/produtos")
public class ProdutoController {

	final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	
	
	
	
}
