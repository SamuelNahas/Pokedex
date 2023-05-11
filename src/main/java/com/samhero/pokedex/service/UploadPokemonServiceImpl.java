package com.samhero.pokedex.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadPokemonServiceImpl implements UploadPokemonService {

	private final Path root = Paths.get("uploads");
	
	@Override
	public void init() {
		try {
			if (!Files.exists(root)) {
				Files.createDirectory(root);
			}			
		} catch (IOException e) {			
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public String salvar(MultipartFile file) {
		try {
			UUID uuid = UUID.randomUUID(); 
			// Gera uma string. Exemplo: 7dc53df5-703e-49b3-8670-b1c468f47f1f
	        String newFileName = uuid.toString(); 
	        // Obtém a extensão do arquivo. Exemplo: jpg
	        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
	        // Cria um novo nome de arquivo. exemplo: 7dc53df5-703e-49b3-8670-b1c468f47f1f.jpg
	        String fileName = newFileName + "." + extension;
	        
			Files.copy(file.getInputStream(), this.root.resolve(fileName));
			
			return fileName;
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}
	}

	@Override
	public boolean apagar(String filename) {
		Path file =  this.root.resolve(filename);
        try {
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	

}
