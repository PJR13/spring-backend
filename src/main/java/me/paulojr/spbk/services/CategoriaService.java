package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.model.Categoria;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(int i) {
		Optional<Categoria> catg = repo.findById(i);
		return catg.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + i + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> listar() {
		return repo.findAll();
	}

}
