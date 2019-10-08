package me.paulojr.spbk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.dto.CategoriaDTO;
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
		List<CategoriaDTO> listDTO = service.listar().stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());
		return new ResponseEntity<List<?>>(listDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> postCatg(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.insert(service.fromDTO(objDto));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> putCatg(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		objDto.setId(id);
		Categoria obj = service.update(service.fromDTO(objDto));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> patchCatg(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		objDto.setId(id);
		Categoria obj = service.update(service.fromDTO(objDto));
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCatg(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<?>> listarPage(@RequestParam(value = "page", defaultValue = "0") Integer i,
			@RequestParam(value = "lines", defaultValue = "24") Integer lines,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		if (service.listar().size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Page<CategoriaDTO> listDTO = service.findPage(i, lines, orderBy, direction.toUpperCase()).map(obj -> new CategoriaDTO(obj));
		return new ResponseEntity<Page<?>>(listDTO, HttpStatus.OK);
	}

}
