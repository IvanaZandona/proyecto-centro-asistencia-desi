package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Long>{

	boolean existsByDni(Integer dni);
	
}
