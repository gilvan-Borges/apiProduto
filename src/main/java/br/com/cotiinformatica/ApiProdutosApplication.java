package br.com.cotiinformatica;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit //habilitar o rabbit
@SpringBootApplication
public class ApiProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiProdutosApplication.class, args);
	}

}
