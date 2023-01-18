package com.project.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan
public class WarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseApplication.class, args);
	}

}

//swagger
//http://localhost:8080/v3/api-docs
//http://localhost:8080/v3/api-docs.yaml
//http://localhost:8080/swagger-ui.html
//cos mi tu nie gra z dokumentacja. jak to zrobilem? pobrlaem yaml, na stronie editor swagger uplad pliku, generate client jako html, lub html 2
