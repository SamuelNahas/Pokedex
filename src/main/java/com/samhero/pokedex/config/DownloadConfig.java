package com.samhero.pokedex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
 * Permite que os arquivos sejam expostos para clientes
 * */
@Configuration
public class DownloadConfig implements WebMvcConfigurer {
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/uploads/**").addResourceLocations("file:uploads/");
    }
}
