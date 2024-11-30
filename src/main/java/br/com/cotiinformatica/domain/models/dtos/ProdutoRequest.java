package br.com.cotiinformatica.domain.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProdutoRequest {
	@NotBlank(message = "Por favor, informe o nome do produto.")
	@Size(min = 8, max = 150, message = "O nome deve ter de 8 a 150 caracteres.")
	private String nome;

	@NotNull(message = "Por favor, informe o preço do produto.")
	@Positive(message = "Por favor, informe o preço com valor maior do que zero.")
	private Double preco;

	@NotNull(message = "Por favor, informe a quantidade do produto.")
	@Min(value = 0, message = "Por favor, informe a quantidade maior ou igual a zero.")
	private Integer quantidade;

	@NotBlank(message = "Por favor, informe a categoria do produto.")
	@Pattern(
		regexp = "INFORMÁTICA|ELETRÔNICOS|VESTUÁRIO|PAPELARIA|OUTROS", 
	    message = "A categoria deve ser INFORMÁTICA ou ELETRÔNICOS ou VESTUÁRIO ou PAPELARIA ou OUTROS."
	    )
	private String categoria;
}
