package br.com.cotiinformatica.domain.models.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Produtos")
public class Produto {
	
	@Id
	private UUID id;
	private String nome;
	private Double preco;
	private Integer quantidade;
	private Categoria categoria;
	private LocalDateTime dataHoraCriacao;
	private LocalDateTime dataHoraUltimaAlteracao;
	private Boolean ativo;
}
