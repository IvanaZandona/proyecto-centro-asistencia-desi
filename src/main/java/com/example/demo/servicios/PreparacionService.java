package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Preparacion;
import com.example.demo.excepciones.Excepcion;

public interface PreparacionService {

	List<Preparacion> getAll();
	
	void save(Preparacion preparacion) throws Excepcion;

	Preparacion filter(Long id); 

	Preparacion getById(Long id); 

	void deleteById(Long id); 

	List<Preparacion> findAll(); 
    
}
