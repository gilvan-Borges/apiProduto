package br.com.cotiinformatica.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.domain.contracts.services.ProdutoService;
import br.com.cotiinformatica.domain.models.dtos.ProdutoRequest;
import br.com.cotiinformatica.domain.models.dtos.ProdutoResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	@Autowired
	ProdutoService produtoService;

	@PostMapping
	public ProdutoResponse post(@RequestBody @Valid ProdutoRequest request) {
		return produtoService.criarProduto(request);
	}

	@PutMapping("{id}")
	public ProdutoResponse put(@PathVariable UUID id, @RequestBody @Valid ProdutoRequest request) {
		return produtoService.alterarProduto(id, request);
	}

	@DeleteMapping("{id}")
	public ProdutoResponse delete(@PathVariable UUID id) {
		return produtoService.inativarProduto(id);
	}

	@GetMapping
	public List<ProdutoResponse> get(@RequestParam(defaultValue = "") String nome,
			@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "20") int tamanho,
			@RequestParam(defaultValue = "nome") String ordenarPor,
			@RequestParam(defaultValue = "ASC") String tipoOrdenacao) {

		if (tamanho > 20) {
			throw new IllegalArgumentException("O tamanho máximo para consulta é de 100 registros.");
		}

		var sortable = Sort.by(Sort.Direction.fromString(tipoOrdenacao), ordenarPor);
		var pageable = PageRequest.of(pagina, tamanho, sortable);

		return produtoService.filtrarProdutos(nome, pageable);
	}

	@GetMapping("{id}")
	public ProdutoResponse get(@PathVariable UUID id) {
		return produtoService.obterProduto(id);
	}
}
