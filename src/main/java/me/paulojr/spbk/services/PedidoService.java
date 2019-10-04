package me.paulojr.spbk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.paulojr.spbk.domain.Pedido;
import me.paulojr.spbk.repositories.PedidoRepository;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(int i) {
		Optional<Pedido> catg = repo.findById(i);
		return catg.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado ID: " + i + ", Tipo: " + Pedido.class.getName()));
	}
	
	public List<Pedido> listar() {
		return repo.findAll();
	}

}
