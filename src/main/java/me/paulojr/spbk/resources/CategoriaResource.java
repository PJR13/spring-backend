package me.paulojr.spbk.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable int id) {
		Categoria cat = service.buscar(id);
		return new ResponseEntity<>(cat, HttpStatus.OK);

	}

	@GetMapping()
	public ResponseEntity<List<?>> listar() {
		if (service.listar().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<?>>(service.listar(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> postCatg(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> putCatg(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> patchCatg(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCatg(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
