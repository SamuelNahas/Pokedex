package com.samhero.pokedex.service;

import java.util.List;

import com.samhero.pokedex.model.Pokemon;

public interface PokemonService {
	
	public void salvarPokemon(Pokemon pokemon);
	public List<Pokemon> buscarPokemon();
	public void apagarPokemonPorId(Long id);
	public Pokemon buscarPokemonPorId(Long id);
	public void editarPokemon(Pokemon pokemon);
	
}
