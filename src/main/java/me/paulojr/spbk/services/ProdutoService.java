package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.model.Produto;
import me.paulojr.spbk.repositories.ProdutoRepository;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	public Produto buscar(int i) {
		Optional<Produto> prod = repo.findById(i);
		return prod.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + i + ", Tipo: " + Produto.class.getName()));
	}

	public List<Produto> listar() {
		return repo.findAll();
	}

}
