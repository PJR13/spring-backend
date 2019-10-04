package me.paulojr.spbk.domain;

import java.util.ArrayList;
import java.util.List;

public class ProdutoNC {

	private Integer id;
	private String nome;
	private Double preco;
	private List<CategoriaNC> catg = new ArrayList<CategoriaNC>();
	
	public ProdutoNC(Produto p) {
		p.getCategorias().forEach(i -> catg.add(new CategoriaNC(i)));
		this.id = p.getId();
		this.nome = p.getNome();
		this.preco = p.getPreco();
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<CategoriaNC> getCatg() {
		return catg;
	}

	public void setCatg(List<CategoriaNC> catg) {
		this.catg = catg;
	}
	
	
}
