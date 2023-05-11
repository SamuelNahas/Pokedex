package com.samhero.pokedex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeControle {
	
	@RequestMapping("/")
	public String index() {
		return "publica-index";
	}
	
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}
}
