package br.com.cotiinformatica.infrastructure.messages;

import java.util.Date;
import java.util.UUID;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.cotiinformatica.domain.contracts.components.ProdutoHistoricoMessage;
import br.com.cotiinformatica.domain.models.dtos.ProdutoHistorico;
import br.com.cotiinformatica.domain.models.dtos.ProdutoHistorico.TipoAcao;
import br.com.cotiinformatica.domain.models.dtos.ProdutoResponse;

@Component
public class ProdutoHistoricoMessageImpl implements ProdutoHistoricoMessage {
	@Autowired
	RabbitTemplate rabbitTemplate;
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	Queue queue;

	@Override
	public void criarHistorico(ProdutoResponse produto, TipoAcao acao) {
		
		var produtoHistorico = new ProdutoHistorico();
		produtoHistorico.setId(UUID.randomUUID());
		produtoHistorico.setDataHora(new Date());
		produtoHistorico.setAcao(acao);
		produtoHistorico.setProduto(produto);
		
		try {

			// converter os dados para JSON (serializar)
			var json = objectMapper.writeValueAsString(produtoHistorico);
			// gravar na fila do rabbitmq
			rabbitTemplate.convertAndSend(queue.getName(), json);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}
}
