package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Preparacion;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.presentacion.PreparacionBuscarForm;

public interface PreparacionService {

	List<Preparacion> getAll();
	
	void save(Preparacion preparacion) throws Excepcion;

	Preparacion getById(Long id); 
	
	List<Preparacion> filter(PreparacionBuscarForm form) throws Excepcion;

	void deleteById(Long id);

	List<Preparacion> findAll(); 
    
}
