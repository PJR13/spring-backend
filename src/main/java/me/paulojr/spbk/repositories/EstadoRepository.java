package me.paulojr.spbk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.paulojr.spbk.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
