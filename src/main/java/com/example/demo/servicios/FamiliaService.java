package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Familia;
import com.example.demo.excepciones.Excepcion;

public interface FamiliaService {

	List<Familia> getAll();
	
	void save(Familia familia) throws Excepcion;

	List<Familia> filter(String nombre); 

	Familia getByNroFamilia(Integer integer); 

	void deleteByNroFamilia(Integer nroFamilia); 

	List<Familia> findAll(); 
    
}
