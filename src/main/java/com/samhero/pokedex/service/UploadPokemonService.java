package com.samhero.pokedex.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadPokemonService {

	public void init();

	public String salvar(MultipartFile file);

	public boolean apagar(String filename);

}
