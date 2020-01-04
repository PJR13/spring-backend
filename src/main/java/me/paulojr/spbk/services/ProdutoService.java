package me.paulojr.spbk.services;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.domain.Produto;
import me.paulojr.spbk.repositories.CategoriaRepository;
import me.paulojr.spbk.repositories.ProdutoRepository;
import me.paulojr.spbk.services.exceptions.IllegalDirectionValueException;
import me.paulojr.spbk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository catgRepo;

    public Page<Produto> search(String name, List<Integer> ids, Integer i, Integer lines, String orderBy, String direction) {
        Sort.Direction d;
        try {
            d = Sort.Direction.valueOf(direction);
        } catch (IllegalArgumentException e) {
            throw new IllegalDirectionValueException("Parametro de direção (direction) inválido, use DESC ou ASC.");
        }
        PageRequest pageRequest = PageRequest.of(i, lines, d, orderBy);
        List<Categoria> catg = catgRepo.findAllById(ids);
        return repo.findDistinctByNomeContainingAndCategoriasIn(name, catg, pageRequest);
    }

    public Produto buscar(int i) {
        Optional<Produto> prod = repo.findById(i);
        return prod.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado ID: " + i + ", Tipo: " + Produto.class.getName()));
    }

    public List<Produto> listar() {
        return repo.findAll();
    }

}
