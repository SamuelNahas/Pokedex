package com.samhero.pokedex.security;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.samhero.pokedex.model.Funcao;
import com.samhero.pokedex.model.Treinador;
import com.samhero.pokedex.service.TreinadorService;

@Service
@Transactional
public class DetalheTreinadorServico implements UserDetailsService {

	@Autowired
	private TreinadorService TreinadorService;
	
	public DetalheTreinadorServico(TreinadorService treinadorService) {
		this.TreinadorService = treinadorService;
	}

	@Override   
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Treinador treinador = TreinadorService.buscarTreinadorPorLogin(username);
		
		if(treinador != null && treinador.isAtivo()) {
			Set<GrantedAuthority> funcoesDoTreinador = new HashSet<GrantedAuthority>();
			for(Funcao funcao: treinador.getFuncoes()) {
				GrantedAuthority ff = new SimpleGrantedAuthority(funcao.getFuncao());
				funcoesDoTreinador.add(ff);
			}			
			User user = new User(treinador.getLogin(), treinador.getPassword(), funcoesDoTreinador);
			return user;
		} else {
			throw new UsernameNotFoundException("Treinador não encontrado");
		}
	}

}
