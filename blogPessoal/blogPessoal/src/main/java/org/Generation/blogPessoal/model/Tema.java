package org.Generation.blogPessoal.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity // transformando a classe Tema em uma entidade do JPA Hibernate
@Table(name = "tb_tema") // criando uma tabela e nomeando-a
public class Tema {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String descricao;

	// criando um relacionamento entre as entidades Tema e Postagem
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL) /*
																 * qual classe, tabela ou atributo está sendo mapeado,
																 * nesse caso, está sendo criado um atributo tema da
																 * tabela de postagens
																 */

	/*
	 * caso seja preciso alterar alguma coisa de um tema especifico, todas as
	 * postagens referentes ao tema escolhido sofrerão alterações
	 */

	@JsonIgnoreProperties("tema")
	private List<Postagem> postagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}
}
