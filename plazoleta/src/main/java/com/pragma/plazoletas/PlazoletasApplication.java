package com.pragma.plazoletas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class PlazoletasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlazoletasApplication.class, args);
	}

}
