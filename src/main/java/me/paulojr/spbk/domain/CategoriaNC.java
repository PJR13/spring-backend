package me.paulojr.spbk.domain;

import java.io.Serializable;

public class CategoriaNC implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoriaNC(Categoria c) {
		this.id = c.getId();
		this.nome = c.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
