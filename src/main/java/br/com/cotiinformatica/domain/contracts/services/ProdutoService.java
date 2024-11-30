package br.com.cotiinformatica.domain.contracts.services;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import br.com.cotiinformatica.domain.models.dtos.ProdutoRequest;
import br.com.cotiinformatica.domain.models.dtos.ProdutoResponse;

public interface ProdutoService {
	
	ProdutoResponse criarProduto(ProdutoRequest request);
	
	ProdutoResponse alterarProduto(UUID id, ProdutoRequest request);
	
	ProdutoResponse inativarProduto(UUID id);
	
	List<ProdutoResponse> filtrarProdutos(String nome, Pageable pageable);
	
	ProdutoResponse obterProduto(UUID id);
}
