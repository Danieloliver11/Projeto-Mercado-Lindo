package com.mercadolindo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.CategoriaEntity;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{

	@Query("SELECT cat FROM CategoriaEntity cat "
			+ " WHERE cat.categoriaPai.id = :id") 
	List<CategoriaEntity> findFilhosByIdPai(Long id);

}
