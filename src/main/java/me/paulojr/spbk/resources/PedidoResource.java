package me.paulojr.spbk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.paulojr.spbk.domain.Pedido;
import me.paulojr.spbk.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable int id) {
		Pedido ped = service.buscar(id);
		return new ResponseEntity<Pedido>(ped, HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<List<Pedido>> listar() {
		if (service.listar().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Pedido>>(service.listar(), HttpStatus.OK);
	}
}
