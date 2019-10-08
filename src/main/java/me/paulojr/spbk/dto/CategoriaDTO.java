package me.paulojr.spbk.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import me.paulojr.spbk.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;


	@Length(min = 5, max = 80, message = "Tamanho máximo de 80 e mínimo de 5 caracteres.")
	@NotEmpty(message = "Preenchimento obrigatório.")
	private String nome;

	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
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
