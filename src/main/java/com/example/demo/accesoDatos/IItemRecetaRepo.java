package com.example.demo.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.ItemReceta;
import com.example.demo.entidades.Receta;


@Repository
public interface IItemRecetaRepo extends JpaRepository<ItemReceta, Long> {
	
	

	List<ItemReceta> findByReceta(Receta receta);
	
	List<ItemReceta> findByIngrediente(Ingrediente ingrediente);
	
	Optional <ItemReceta> findByRecetaAndIngrediente (Receta receta, Ingrediente ingrediente);
	
	void deleteByReceta (Receta receta);
	
	
}
