package com.mercadolindo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mercadolindo.entity.EnderecoEntity;
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

}
