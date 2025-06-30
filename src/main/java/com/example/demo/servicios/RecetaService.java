package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.Receta;
import com.example.demo.excepciones.Excepcion;

public interface RecetaService {

	List<Receta> getAll();

	void save(Receta receta) throws Exception;

	void deleteById(Long id) throws Excepcion;

	Receta getById(Long id) throws Excepcion;

}
