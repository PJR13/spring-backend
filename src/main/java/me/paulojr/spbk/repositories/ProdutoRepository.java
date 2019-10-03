package me.paulojr.spbk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.paulojr.spbk.model.Produto;
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	

}
