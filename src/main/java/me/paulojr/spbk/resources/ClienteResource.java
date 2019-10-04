package me.paulojr.spbk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.paulojr.spbk.domain.Cliente;
import me.paulojr.spbk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable int id){
		Cliente cli = service.buscar(id);
		return new ResponseEntity<Cliente>(cli, HttpStatus.OK);
		
	}

	@GetMapping()
	public ResponseEntity<List<Cliente>> listar(){
		if(service.listar().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cliente>>(service.listar(), HttpStatus.OK);
	}
}
