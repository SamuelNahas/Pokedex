package com.samhero.pokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhero.pokedex.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long>{
	Funcao findByFuncao(String funcao);
}
