package br.com.cotiinformatica.domain.models.dtos;

import java.util.UUID;
import lombok.Data;

@Data
public class ProdutoResponse {
	private UUID id;
	private String nome;
	private Double preco;
	private Integer quantidade;
	private Double total;
	private String categoria;
}
