package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.repositories.ClienteRepository;
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

}
