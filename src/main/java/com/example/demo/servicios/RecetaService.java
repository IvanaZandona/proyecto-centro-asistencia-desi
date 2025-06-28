package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Receta;

public interface RecetaService {

	List<Receta> getAll();

	void save(Receta receta) throws Exception;


}
