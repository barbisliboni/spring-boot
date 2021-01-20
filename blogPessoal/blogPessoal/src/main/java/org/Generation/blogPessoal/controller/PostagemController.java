package org.Generation.blogPessoal.controller;

import java.util.List;

import org.Generation.blogPessoal.model.Postagem;
import org.Generation.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/************************************************/

	@PostMapping
	/*
	 * o PostMapping é usado para lidar com o tipo POST do método da requisição
	 */

	// post é o nome do método
	// Postagem em maiusculo é o parametro do método
	// postagem em minusculo é o nome que damos ao parametro
	/*
	 * quando utilizamos um objeto grande e seguro, no caso, que o usuario nao
	 * poderá ter acesso/ver, passaremos pelo corpo da requisição feita, logo,
	 * usaremos o @RequestBody
	 */
	public ResponseEntity<Postagem> post(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		/*
		 * salvando na base de dados e retornando no body, obtendo assim, o end point de
		 * postagem
		 */
		/*
		 * nao usaremos o .ok, que atende ao protocolo http 200, e sim, o 201, para
		 * isso, usaremos o CREATED
		 * 
		 * colocar o .body e chamar o repositorio, apos, chamar o método save, e colocar
		 * como parametro a entidade postagem (pois postagem é o que foi recebido pelo
		 * client)
		 */

	}

	/************************************************/

	@PutMapping
	/*
	 * o PutMapping é usado para lidar com o tipo PUT do método da requisição, no
	 * caso do CRUD, o POST seria o UPDATE
	 */

	public ResponseEntity<Postagem> put(@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}

	/************************************************/

	@DeleteMapping("/{id}")
	/*
	 * o DeleteMapping é usado para lidar com o tipo DELETE do método da requisição
	 * o parametro é o recurso que será deletado, o qual sera escolhido pelo id
	 */
	public void delete(@PathVariable long id) { // como é void, nao utlizo a palavra reservada return
		repository.deleteById(id);
		// resposta padrao (ok) caso de certo, e uma mensagem de erro caso não funcione
	}

}
