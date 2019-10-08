package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.dto.ClienteDTO;
import me.paulojr.spbk.repositories.ClienteRepository;
import me.paulojr.spbk.services.exceptions.DataIntegrityException;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(int i) {
		Optional<Cliente> cli = repo.findById(i);
		return cli.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + i + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> listar() {
		return repo.findAll();
	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		return repo.save(updateData(newObj, obj));
	}

	public Page<Cliente> findPage(Integer i, Integer lines, String orderBy, String direction) {
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
			throw new DataIntegrityException("Não é possível deletar um Cliente que possui pedidos.");
		}

	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}

	private Cliente updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		return newObj;
	}

}
