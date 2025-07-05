package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Asistido;

public interface IAsistidoRepo extends JpaRepository<Asistido, Long>{

	 boolean existsByDni(Integer dni);
	 
}
