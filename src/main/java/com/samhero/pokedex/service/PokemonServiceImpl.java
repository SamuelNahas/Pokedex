package com.samhero.pokedex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samhero.pokedex.model.Pokemon;
import com.samhero.pokedex.repository.PokemonRepository;

@Service
public class PokemonServiceImpl implements PokemonService {

	@Autowired
	private PokemonRepository PokemonRepository;

	@Override
	public void salvarPokemon(Pokemon pokemon) {
		PokemonRepository.save(pokemon);
	}

	@Override
	public List<Pokemon> buscarPokemon() {
		return PokemonRepository.findAll();
	}

	@Override
	public void apagarPokemonPorId(Long id) {
		Pokemon pokemon = buscarPokemonPorId(id);
		PokemonRepository.delete(pokemon);

	}

	@Override
	public Pokemon buscarPokemonPorId(Long id) {
		Optional<Pokemon> pokemon = PokemonRepository.findById(id);
		if (pokemon.isPresent()) {
			return pokemon.get();
		} else {
			throw new IllegalArgumentException("pokemon com id : " + id + " não existe");
		}
	}
	
	@Override 
	public void editarPokemon(Pokemon pokemon) {
		PokemonRepository.save(pokemon);
		
	}

}
