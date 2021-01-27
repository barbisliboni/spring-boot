package com.gamestore.gameStore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 5, max = 35)
	private String nomeJogo;
	
	@NotNull
	private float precoJogo;
	
	@NotNull
	private boolean online;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	
	private Categoria categoria;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNomeJogo() {
		return nomeJogo;
	}
	
	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
	
	public float getPrecoJogo() {
		return precoJogo;
	}
	
	public void setPrecoJogo(float precoJogo) {
		this.precoJogo = precoJogo;
	}
	
	public boolean isOnline() {
		return online;
	}
	
	public void setOnline(boolean online) {
		this.online = online;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
