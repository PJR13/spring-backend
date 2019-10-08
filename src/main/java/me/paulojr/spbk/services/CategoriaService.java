package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.dto.CategoriaDTO;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.services.exceptions.DataIntegrityException;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
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

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}

	public Page<Categoria> findPage(Integer i, Integer lines, String orderBy, String direction) {
		Direction d;
		try {
			d = Direction.valueOf(direction);
		} catch (IllegalArgumentException e) {
			throw new IllegalDirectionValueException("Parametro de direção (direction) inválido, use DESC ou ASC.");
		}
		PageRequest pageRequest = PageRequest.of(i, lines, d, orderBy);
		return repo.findAll(pageRequest);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível deletar uma categoria que possui um produto.");
		}

	}
	public Categoria fromDTO(CategoriaDTO dto) {
		return new Categoria(dto.getId(), dto.getNome());
	}

}
