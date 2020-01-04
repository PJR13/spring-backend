package me.paulojr.spbk.resources;

import me.paulojr.spbk.domain.Produto;
import me.paulojr.spbk.dto.ProdutoDTO;
import me.paulojr.spbk.resources.utils.URLUtils;
import me.paulojr.spbk.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> find(@PathVariable int id) {
        Produto prod = service.buscar(id);
        return new ResponseEntity<>(prod, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarPage(@RequestParam(value = "nome", defaultValue = "") String nome, @RequestParam(value = "categorias", defaultValue = "") String categorias, @RequestParam(value = "page", defaultValue = "0") Integer i,
                                                       @RequestParam(value = "lines", defaultValue = "24") Integer lines,
                                                       @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                       @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        if (service.listar().size() == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Page<ProdutoDTO> listDTO = service.search(URLUtils.decodeParam(nome), URLUtils.decodeIntList(categorias), i, lines, orderBy, direction.toUpperCase()).map(ProdutoDTO::new);
        return new ResponseEntity<>(listDTO, HttpStatus.OK);
    }
}
