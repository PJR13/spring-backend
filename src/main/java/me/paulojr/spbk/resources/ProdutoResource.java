package me.paulojr.spbk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.paulojr.spbk.domain.Produto;
import me.paulojr.spbk.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable int id) {
		Produto prod = service.buscar(id);
		return new ResponseEntity<Produto>(prod, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Produto>> listar() {
		if (service.listar().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Produto>>(service.listar(), HttpStatus.OK);
	}

}
