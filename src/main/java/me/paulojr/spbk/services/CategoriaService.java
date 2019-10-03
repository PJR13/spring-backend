package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.model.Categoria;
import me.paulojr.spbk.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(int i) {
		Optional<Categoria> catg = repo.findById(i);
		return catg.orElse(null);
	}

	
	public List<Categoria> listar() {
		return repo.findAll();
	}
	
}
