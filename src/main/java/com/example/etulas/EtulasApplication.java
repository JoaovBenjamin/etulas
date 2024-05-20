package com.example.etulas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "Etulas",
		summary = "API Rest do projeto Etulas",
		description = "API Rest do projeto Etulas"
	)
)
public class EtulasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtulasApplication.class, args);
	}

}
