package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Ingrediente;

public interface IngredienteService {

	List<Ingrediente> getAll();


	void save(Ingrediente ingrediente, Long idCondimento, Long idProducto) throws Exception;


	void saveProducto(String nombre, Integer calorias, Float stock, Float precio) throws Exception;

	
}
