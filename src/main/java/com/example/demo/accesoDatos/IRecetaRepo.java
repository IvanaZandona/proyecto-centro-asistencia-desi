package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Receta;

import java.util.List;

@Repository
public interface IRecetaRepo extends JpaRepository<Receta, Long> {
	
	
	//List<Receta> findByNombre(String nombre);
	
	//Receta findFirstById(Long id);
	
	
	}


 