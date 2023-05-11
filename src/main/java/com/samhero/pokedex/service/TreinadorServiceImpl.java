package com.samhero.pokedex.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samhero.pokedex.model.Treinador;
import com.samhero.pokedex.repository.TreinadorRepository;

@Service
public class TreinadorServiceImpl implements TreinadorService {

	@Autowired
	private TreinadorRepository TreinadorRepository;

	@Override
	public void salvarTreinador(Treinador treinador) {
		TreinadorRepository.save(treinador);
	}

	@Override
	public List<Treinador> buscarTreinador() {
		return TreinadorRepository.findAll();
	}

	@Override
	public void apagarTreinadorPorId(Long id) {
		Treinador treinador = buscarTreinadorPorId(id);
		TreinadorRepository.delete(treinador);

	}

	@Override
	public Treinador buscarTreinadorPorId(Long id) {
		Optional<Treinador> treinador = TreinadorRepository.findById(id);
		if (treinador.isPresent()) {
			return treinador.get();
		} else {
			throw new IllegalArgumentException("treinador com id : " + id + " não existe");
		}
	}

	@Override
	public Treinador buscarTreinadorPorLogin(String login) {
		Optional<Treinador> treinador = TreinadorRepository.findByLogin(login);
		if (treinador.isPresent()) {
			return treinador.get();
		} else {
			throw new IllegalArgumentException("treinador com login : " + login + " não existe");
		}
	}

}
  