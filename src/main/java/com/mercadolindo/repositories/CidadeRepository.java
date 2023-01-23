package com.mercadolindo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.CidadeEntity;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

}
