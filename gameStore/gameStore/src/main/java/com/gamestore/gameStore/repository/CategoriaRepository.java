package com.gamestore.gameStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamestore.gameStore.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	public List<Categoria> findAllByTipoJogoContainingIgnoreCase(String tipoJogo);
}
