package com.samhero.pokedex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity	
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@NotEmpty(message = "Tipo é obrigatório")
	private String tipo;

	@Min(value = 1, message = "Nível deve ser no mínimo 1")
	@Max(value =100, message = "Nível deve ser no maximo 100")
	private int nivel;

	@ManyToMany(mappedBy = "pokemons")
	private List<Treinador> treinadores;

	private String url;

	private String nomeArquivo;

	// getters e setters

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public List<Treinador> getTreinadores() {
		return treinadores;
	}

	public void setTreinadores(List<Treinador> treinadores) {
		this.treinadores = treinadores;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}