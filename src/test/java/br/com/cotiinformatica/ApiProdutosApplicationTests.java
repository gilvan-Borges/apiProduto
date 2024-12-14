package br.com.cotiinformatica;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import br.com.cotiinformatica.application.controllers.ProdutoController;
import br.com.cotiinformatica.domain.models.dtos.ProdutoRequest;
import br.com.cotiinformatica.domain.models.dtos.ProdutoResponse;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ApiProdutosApplicationTests {

	@Autowired
	MockMvc mockMvc;
	@InjectMocks
	ProdutoController produtoController;

	@Test
	void cadastrarProdutoTest() throws Exception {

		var faker = new Faker();

		var request = new ProdutoRequest();
		request.setNome(faker.commerce().productName());
		request.setPreco((double) faker.number().numberBetween(100, 1000));
		request.setQuantidade(faker.number().numberBetween(1, 100));
		request.setCategoria("INFORMÁTICA");

		var response = new ProdutoResponse();
		response.setNome(request.getNome());
		response.setPreco(request.getPreco());
		response.setQuantidade(request.getQuantidade());
		response.setCategoria(request.getCategoria());

		mockMvc.perform(post("/api/produtos").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome").value(response.getNome()))
				.andExpect(jsonPath("$.preco").value(response.getPreco()))
				.andExpect(jsonPath("$.quantidade").value(response.getQuantidade()))
				.andExpect(jsonPath("$.categoria").value(response.getCategoria()));
	}
}
