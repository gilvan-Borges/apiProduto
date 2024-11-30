package br.com.cotiinformatica.domain.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.domain.contracts.services.ProdutoService;
import br.com.cotiinformatica.domain.models.dtos.ProdutoRequest;
import br.com.cotiinformatica.domain.models.dtos.ProdutoResponse;
import br.com.cotiinformatica.domain.models.entities.Categoria;
import br.com.cotiinformatica.domain.models.entities.Produto;
import br.com.cotiinformatica.infrastructure.repositories.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;

	@Override
	public ProdutoResponse criarProduto(ProdutoRequest request) {
		var produto = new Produto();

		produto.setId(UUID.randomUUID());
		produto.setNome(request.getNome());
		produto.setPreco(request.getPreco());
		produto.setQuantidade(request.getQuantidade());
		produto.setCategoria(Categoria.valueOf(request.getCategoria()));
		produto.setDataHoraCriacao(LocalDateTime.now());
		produto.setDataHoraUltimaAlteracao(LocalDateTime.now());
		produto.setAtivo(true);

		produtoRepository.save(produto);
		return toResponse(produto);
	}

	@Override
	public ProdutoResponse alterarProduto(UUID id, ProdutoRequest request) {
		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado para edição. Verifique o ID."));

		produto.setNome(request.getNome());
		produto.setPreco(request.getPreco());
		produto.setQuantidade(request.getQuantidade());
		produto.setCategoria(Categoria.valueOf(request.getCategoria()));
		produto.setDataHoraUltimaAlteracao(LocalDateTime.now());

		produtoRepository.save(produto);
		return toResponse(produto);
	}

	@Override
	public ProdutoResponse inativarProduto(UUID id) {
		var produto = produtoRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("Produto não encontrado para inativação. Verifique o ID."));

		produto.setAtivo(false);
		produto.setDataHoraUltimaAlteracao(LocalDateTime.now());

		produtoRepository.save(produto);
		return toResponse(produto);
	}

	@Override
	public List<ProdutoResponse> filtrarProdutos(String nome, Pageable pageable) {
		var page = produtoRepository.find(nome, pageable);

		var lista = new ArrayList<ProdutoResponse>();
		for (var produto : page.getContent()) {
			lista.add(toResponse(produto));
		}

		return lista;
	}

	@Override
	public ProdutoResponse obterProduto(UUID id) {
		var produto = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Produto não encontrado. Verifique o ID."));

		return toResponse(produto);
	}

	private ProdutoResponse toResponse(Produto produto) {

		var response = new ProdutoResponse();

		response.setId(produto.getId());
		response.setNome(produto.getNome());
		response.setPreco(produto.getPreco());
		response.setQuantidade(produto.getQuantidade());
		response.setTotal(produto.getPreco() * produto.getQuantidade());
		response.setCategoria(produto.getCategoria().toString());

		return response;
	}
}
