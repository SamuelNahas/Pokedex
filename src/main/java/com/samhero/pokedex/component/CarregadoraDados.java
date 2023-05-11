package com.samhero.pokedex.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.samhero.pokedex.model.Funcao;
import com.samhero.pokedex.repository.FuncaoRepository;

@Component
public class CarregadoraDados implements CommandLineRunner {

	@Autowired
	private FuncaoRepository funcaoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] funcaoes = {"ADMIN", "USER", "LIDER"};
		
		for (String funcaoString: funcaoes) {
			Funcao funcao = funcaoRepository.findByFuncao(funcaoString);
			if (funcao == null) {
				funcao = new Funcao(funcaoString);
				funcaoRepository.save(funcao);
			}
		}				
	}

}

