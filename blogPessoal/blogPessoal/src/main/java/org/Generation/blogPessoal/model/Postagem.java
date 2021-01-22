package org.Generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*anotacoes parâmetros acima das classes e/ou propriedades que 
definem o comportamento das mesmas*/

@Entity // entidade do JPA Hibernate
@Table(name = "postagem") /*
							 * informando que a entidade dentro do banco de dados irá virar uma tabela e,
							 * dentro, defino como argumento o nome da mesma
							 */

public class Postagem {

	@Id // atribuindo comportamentos para o id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * informando que o id sera um valor gerado e transformado em PK
	 */
	private long id;

	@NotNull // nao permitir enviar dados vazios
	@Size(min = 5, max = 100)
	/*
	 * determinar a quantidade de caracteres que o usuario enviará como título
	 */
	private String titulo;

	private String texto;

	@Temporal(TemporalType.TIMESTAMP)
	/*
	 * indicara para o JPA Hibernate que o programa está trabalhando com tempo
	 */
	private Date date = new java.sql.Date(System.currentTimeMillis());

	@ManyToOne
	@JsonIgnoreProperties("postagem") /*
										 * a propriedade que será ignorada dentro de Tema será a postagem, ou seja,
										 * quando chegar em postagem, o programa parará de mostrar informações
										 */
	private Tema tema;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
}
