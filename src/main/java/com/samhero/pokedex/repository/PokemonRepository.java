package com.samhero.pokedex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhero.pokedex.model.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>{

}
