package org.Generation.blogPessoal.repository;

import java.util.List;

import org.Generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*explicando para o Spring que esta classe 
 * se trata de uma classe de repositorio
 */
@Repository

/*
 * Postagem é a entidade que utilizaremos com a PK do tipo Long
 */
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

	// consulta por tudo que ha na entidade titulo
	/*
	 * busca todos pelo titulo contendo o ignore case, que, no caso, nao levará em
	 * consideracao caracteres maiusculos e minusculos, padronizando tudo para
	 * minusculo
	 */
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);
}
