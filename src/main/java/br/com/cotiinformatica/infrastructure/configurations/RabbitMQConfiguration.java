package br.com.cotiinformatica.infrastructure.configurations;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
	/*
	 * Pegando o nome da fila mapeado na variável do application.properties
	 */
	@Value("${queue.name}")
	private String queueName;

	/*
	 * Método para definir o nome da fila que iremos acessar no servidor de
	 * mensageria
	 */
	@Bean
	Queue queue() {
		// definir o nome da fila -> queueName
		// true -> definir a fila como 'duravel', ou seja, ela não será
		// apagada mesmo que o servidor seja parado
		return new Queue(queueName, true);
	}
}
