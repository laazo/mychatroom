package com.assessment.azolachat;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AzolaChatApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(AzolaChatApplication.class, args);
	}
}
