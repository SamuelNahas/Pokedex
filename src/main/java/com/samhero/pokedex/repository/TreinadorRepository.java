package com.samhero.pokedex.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samhero.pokedex.model.Treinador;

@Repository
public interface TreinadorRepository extends JpaRepository<Treinador, Long>{
 Optional<Treinador> findByLogin (String login); 
}
