package com.samhero.pokedex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Treinador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@CPF(message = "CPF inválido")
	private String cpf;

	@Min(value = 0, message = "quantidade de insignias é obrigatório")
	private int qtde_insignias;

	@NotBlank(message = "A senha deve ser informada")
	@Size(min = 3, message = "A senha deve ter no mínimo 3 caracteres")
	private String password;

	@NotBlank(message = "O login deve ser informado")
	@Size(min = 4, message = "O login deve ter no mínimo 4 caracteres")
	private String login;

	private boolean ativo;

	@ManyToMany
	@JoinTable(name = "treinador_pokemon", joinColumns = @JoinColumn(name = "treinador_id"), inverseJoinColumns = @JoinColumn(name = "pokemon_id"))
	private List<Pokemon> pokemons;

	// Faço um relacionamento onde um treinador tem varias funções e uma função pode
	// ter varios treinadores
	@ManyToMany(fetch = FetchType.EAGER)
	// nesse trecho eu junto as duas tabelas criando uma terceira com as chaves
	// estrangeiras de ambas
	@JoinTable(name = "treinador_funcao", joinColumns = @JoinColumn(name = "treinador_id"),
			// Escolho a coluna que faz referência a outra tabela ( no caso função)
			inverseJoinColumns = @JoinColumn(name = "funcao_id"))
	private List<Funcao> funcoes;

	// getters e setters

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getQtde_insignias() {
		return qtde_insignias;
	}

	public void setQtde_insignias(int qtde_insignias) {
		this.qtde_insignias = qtde_insignias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Pokemon> getPokemons() {
		return pokemons;
	}

	public void setPokemons(List<Pokemon> pokemons) {
		this.pokemons = pokemons;
	}

	public List<Funcao> getFuncoes() {
		return funcoes;
	}

	public void setFuncoes(List<Funcao> funcoes) {
		this.funcoes = funcoes;
	}
	

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
