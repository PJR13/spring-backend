package me.paulojr.spbk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.paulojr.spbk.domain.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	

}
