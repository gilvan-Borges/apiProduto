package br.com.cotiinformatica.domain.models.dtos;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class ProdutoHistorico {
	private UUID id;
	private Date dataHora;
	private TipoAcao acao;
	private ProdutoResponse produto;

	public enum TipoAcao {
		CADASTRO,
		EDICAO,
		EXCLUSAO
	}
}
