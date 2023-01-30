package com.mercadolindo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends CrudRepository<ProdutoEntity, Long>{

	Page<ProdutoEntity> findAll(Specification<ProdutoEntity> specs, Pageable pageRequest);

}
