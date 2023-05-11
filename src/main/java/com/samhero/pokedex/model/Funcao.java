package com.samhero.pokedex.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Funcao {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		private String funcao;
		
		//Faço mapeamento dizendo que esta classe se relacona com o atributo funcoes que faz parte da classe treinadores
		//alem de usar o fethtype.eager que carrega as duas entidades automaticamente
		@ManyToMany(mappedBy = "funcoes", fetch = FetchType.EAGER)
		private List<Treinador> treinadores;
		
		public Funcao() {}

		public Funcao(String funcao) {
			super();
			this.funcao = funcao;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFuncao() {
			return funcao;
		}

		public void setFuncao(String funcao) {
			this.funcao = funcao;
		}

		public List<Treinador> getTreinador() {
			return treinadores;
		}

		public void setTreinadores(List<Treinador> treinadores) {
			this.treinadores = treinadores;
		}
		
		
	}


