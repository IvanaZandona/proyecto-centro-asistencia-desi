package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Receta;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRecetaRepo extends JpaRepository<Receta, Long> {
	
	
	 Optional<Receta> findByNombre(String nombre);
	
	 Optional<Receta> findOneByNombre(String nombre);
	 
	 boolean existsByNombre(String nombre);

	List<Receta> findByActivaTrue();
	
	}


 