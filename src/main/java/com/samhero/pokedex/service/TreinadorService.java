package com.samhero.pokedex.service;

import java.util.List;

import com.samhero.pokedex.model.Treinador;

public interface TreinadorService {

	public void salvarTreinador(Treinador treinador);
	public List<Treinador> buscarTreinador();
	public void apagarTreinadorPorId(Long id);
	public Treinador buscarTreinadorPorId(Long id);
	public Treinador buscarTreinadorPorLogin(String login);
}
