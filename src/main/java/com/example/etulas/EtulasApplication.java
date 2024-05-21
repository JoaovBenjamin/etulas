package com.example.etulas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@Controller
@OpenAPIDefinition(
	info = @Info(
		title = "Etulas",
		summary = "API Rest do projeto Etulas",
		description = "API Rest do projeto Etulas",
		version = "1.0.0"
	)
)
public class EtulasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtulasApplication.class, args);
	}

}
