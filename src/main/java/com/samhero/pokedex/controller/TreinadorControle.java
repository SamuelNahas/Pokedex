package com.samhero.pokedex.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.samhero.pokedex.model.Funcao;
import com.samhero.pokedex.model.Treinador;
import com.samhero.pokedex.repository.FuncaoRepository;
import com.samhero.pokedex.service.FuncaoService;
import com.samhero.pokedex.service.TreinadorService;

@Controller
@RequestMapping("/treinador")
public class TreinadorControle {

	@Autowired
	private TreinadorService treinadorService;

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Autowired
	private FuncaoService funcaoService;

	@GetMapping("/novo")
	public String novo(Model model) {
		model.addAttribute("treinador", new Treinador());
		return "publica-criar-treinador";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Treinador treinador, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "publica-criar-treinador";
		}
		Treinador tre = treinadorService.buscarTreinadorPorLogin(treinador.getLogin());
		if (tre != null) {
			model.addAttribute("loginExiste", "Login já exise cadastrado");
			return "/publica-criar-treinador";
		}																									

		Funcao funcao = funcaoRepository.findByFuncao("USER");
		List<Funcao> funcoes = new ArrayList<Funcao>();
		funcoes.add(funcao);
		treinador.setFuncoes(funcoes);

		treinadorService.salvarTreinador(treinador);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return "redirect:/treinador/novo";
	}

	@RequestMapping("/admin/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("treinadores", treinadorService.buscarTreinador());
		return "/auth/admin/admin-listar-treinador";
	}

	@GetMapping("/admin/apagar/{id}")
	public String deletarPokemon(@PathVariable("id") long id, Model model) {
		treinadorService.apagarTreinadorPorId(treinadorService.buscarTreinadorPorId(id).getId());
		return "redirect:/treinador/admin/listar";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
		if (treinadorService.buscarTreinadorPorId(id) == null) {
			throw new IllegalArgumentException("Usuário inválido:" + id);
		}
		model.addAttribute("treinador", treinadorService.buscarTreinadorPorId(id));
		return "/auth/user/user-alterar-treinador";
	}

	@PostMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, @Valid Treinador treinador, BindingResult result) {
		if (result.hasErrors()) {
			treinador.setId(id);
			return "redirect:/auth/user/user-alterar-usuario";
		}
		treinadorService.salvarTreinador(treinador);
		return "redirect:/treinador/admin/listar";
	}

	@GetMapping("/editarFuncao/{id}")
	public String selecionarPapel(@PathVariable("id") long id, Model model) {
		if (treinadorService.buscarTreinadorPorId(id) == null) {
			return "redirect:/treinador/";
		}
		model.addAttribute("treinador", treinadorService.buscarTreinadorPorId(id));
		model.addAttribute("listaFuncoes", funcaoRepository.findAll());

		return "/auth/admin/admin-alterar-funcao-treinador";
	}

	@PostMapping("/editarFuncao/{id}")
	public String atribuirPapel(@PathVariable("id") long idTreinador,
			@RequestParam(value = "pps", required = false) long[] pps, Treinador treinador,
			RedirectAttributes attributes, Model model) {
		if (pps == null || pps.length == 0) {
			treinador.setId(idTreinador);
			attributes.addFlashAttribute("mensagem", "Pelo menos um papel deve ser informado");
			return "redirect:redirect:/treinador/editarFuncao/" + idTreinador;
		} else {
			// Obtém a lista de papéis selecionada pelo usuário do banco
			List<Funcao> funcoes = Arrays.stream(pps).mapToObj(id -> funcaoService.buscarFuncaoPorId(id)).collect(Collectors.toList());
			for (int i = 0; i < pps.length; i++) {
				long idFuncao = pps[i];
				Funcao funcao = funcaoService.buscarFuncaoPorId(idFuncao);
				funcoes.add(funcao);
			}

			treinador.setFuncoes(funcoes);
			treinadorService.buscarTreinadorPorId(idTreinador).setAtivo(treinador.isAtivo());
			treinadorService.salvarTreinador(treinadorService.buscarTreinadorPorId(idTreinador));

		}
		return "redirect:/treinador/admin/listar";
	}

}