package com.samhero.pokedex.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.samhero.pokedex.model.Funcao;
import com.samhero.pokedex.model.Treinador;
import com.samhero.pokedex.service.TreinadorService;


@Component
public class LoginSucesso extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	private TreinadorService treinadorService;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

		// pega o login do usuário logado
		String login = authentication.getName(); 
		//- busca o usuário no banco pelo login
		Treinador treinador = treinadorService.buscarTreinadorPorLogin(login);	
		
		String redirectURL = "";
		if (temAutorizacao(treinador, "ADMIN")) {
            redirectURL = "/auth/admin/admin-index";
        } else if (temAutorizacao(treinador, "USER")) {
            redirectURL = "/auth/user/user-index";
        } else if (temAutorizacao(treinador, "BIBLIOTECARIO")) {
            redirectURL = "/auth/biblio/biblio-index";
        }		
        response.sendRedirect(redirectURL);         
    }
	/**
	 * Método que verifica qual papel o usuário tem na aplicação 
	 * */
	private boolean temAutorizacao(Treinador treinador, String funcao) {
		for (Funcao ff : treinador.getFuncoes()) {
			if (ff.getFuncao().equals(funcao)) {
				return true;
			}
	    }
		return false;
	}
}

