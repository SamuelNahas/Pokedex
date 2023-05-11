package com.samhero.pokedex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.samhero.pokedex.model.Funcao;
import com.samhero.pokedex.repository.FuncaoRepository;

public class FuncaoServiceImpl implements FuncaoService{

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Override
	public Funcao buscarFuncaoPorId(Long id) {
		Optional<Funcao> funcao = funcaoRepository.findById(id);
		if (funcao.isPresent()) {
			return funcao.get();
		} else {
			throw new IllegalArgumentException("funcao com id : " + id + " não existe");
		}
	}



}
