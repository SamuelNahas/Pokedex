package com.samhero.pokedex.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.samhero.pokedex.model.Pokemon;
import com.samhero.pokedex.service.PokemonService;
import com.samhero.pokedex.service.UploadPokemonService;

@Controller
@RequestMapping("/pokemon")
public class PokemonControle {

	@Autowired
	private PokemonService pokemonService;
	
	@Autowired
	private UploadPokemonService uploadPokemonService;

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("pokemon", new Pokemon());
		return "publica-criar-pokemon";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Pokemon pokemon, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "publica-criar-pokemon";
		}
		pokemonService.salvarPokemon(pokemon);
		attributes.addFlashAttribute("mensagem", "Pokemon salvo com sucesso!");
		return "redirect:/pokemon/novo";
	}

	@PostMapping("/gravar")
	public String gravarImagem(Pokemon pokemon, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			Model model, RedirectAttributes attributes) {
		String fileName = uploadPokemonService.salvar(file);
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
		String url = baseUrl + "/uploads/" + fileName;
		pokemon.setUrl
		(url);
		pokemon.setNomeArquivo(fileName);
		pokemonService.salvarPokemon(pokemon);
		attributes.addFlashAttribute("mensagem", "Imagem salva com sucesso.");
		return "redirect:/pokemon/novo";
	}

	@RequestMapping("/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("pokemons", pokemonService.buscarPokemon());
		return "/publica-listar-pokemon";	
	}

	@GetMapping("/apagar/{id}")
	public String deletarPokemon(@PathVariable("id") long id, Model model) {
		pokemonService.apagarPokemonPorId(pokemonService.buscarPokemonPorId(id).getId());
		return "redirect:/pokemon/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		if (pokemonService.buscarPokemonPorId(id) == null) {
			return "redirect:/pokemon/";
		}
		model.addAttribute("pokemon", pokemonService.buscarPokemonPorId(id));
		return "publica-alterar-pokemon";
	}

	@PostMapping("/editar/{id}")
	public String editar(@PathVariable("id") Pokemon pokemon, @RequestParam("file") MultipartFile file, HttpServletRequest request,
			Model model, RedirectAttributes attributes) {
		String fileName = uploadPokemonService.salvar(file);
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
		String url = baseUrl + "/uploads/" + fileName;
		pokemon.setUrl
		(url);
		pokemon.setNomeArquivo(fileName);
		pokemonService.salvarPokemon(pokemon);
		pokemonService.salvarPokemon(pokemon);
		return "redirect:/pokemon/listar";
	}

}