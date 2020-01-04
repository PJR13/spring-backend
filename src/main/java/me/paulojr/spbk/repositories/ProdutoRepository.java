package me.paulojr.spbk.repositories;

import me.paulojr.spbk.domain.Categoria;
import me.paulojr.spbk.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//  @Query("select distinct obj from Produto obj inner join obj.categorias cat where obj.nome like %:nome% and cat in :categorias")
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String name, List<Categoria> catg, Pageable pageable);


}
