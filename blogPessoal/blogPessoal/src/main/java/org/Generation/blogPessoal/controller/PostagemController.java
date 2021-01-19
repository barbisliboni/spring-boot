package org.Generation.blogPessoal.controller;

import java.util.List;

import org.Generation.blogPessoal.model.Postagem;
import org.Generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // informa para a spring que essa classe se trata de um controller
@RequestMapping("/postagens") // define por qual URL que a classe será acessada
@CrossOrigin("*") // faz a API aceitar a origem do front end

public class PostagemController {

	/*
	 * Autowired a Spring fica responsável de instanciar a classe, garantindo que
	 * todos os serviços dessa interface do tipo PostagemRepository sejam acessados
	 * a partir do Controller
	 */
	@Autowired

	// injetar a classe de repositorio na classe de controller
	private PostagemRepository repository;

	@GetMapping
	/*
	 * sempre que vir uma requisição externa, se o método da mesma for um método
	 * Get, o método abaixo irá rodar
	 */

	/*
	 * método de findAll será do tipo ResponseEntity retornará uma lista do tipo
	 * Postagem o nome do método é GetAll
	 */
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
		/*
		 * irá retornar um objeto do tipo ResponseEntity ok é a resposta HTTP dentro do
		 * argumento do ok, fazer a requisição de todas as postagens
		 */
	}

	// determinar o método HTTP que sera enviado para a API
	@GetMapping("/{id}")
	/*
	 * especificando qual parametro sera enviado por quem estiver fazendo a
	 * requisição, no caso, esperar o parametro id
	 */

	/*
	 * o valor que entrara na variável id deve vir como uma variavel do caminho da
	 * URI
	 */
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

		/*
		 * o findById pode devolver tanto um objeto do tupo postagem (ok), quanto um
		 * notFound caso esse objeto nao exista ou caso haja algum erro na requisição
		 */
	}

	@GetMapping("/titulo/{titulo}")
	// sub caminho para não dar duplicação de end point

	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
}
