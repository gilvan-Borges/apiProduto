package br.com.cotiinformatica.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.domain.models.entities.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, UUID> {
	@Query("{ 'nome' : { $regex: ?0, $options: 'i' }, 'ativo' : true }")
	Page<Produto> find(String nome, Pageable pageable);
}
