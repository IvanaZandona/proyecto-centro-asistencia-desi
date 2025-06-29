package com.example.demo.accesoDatos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Preparacion;

@Repository
public interface IPreparacionRepo extends JpaRepository<Preparacion, Long> {

	// Buscar por Id de preparación
    @Query("SELECT p FROM Preparacion p WHERE p.id = :idpreparacion")
    Preparacion buscarPorId(Long idpreparacion);

    // Buscar por fecha exacta de cocción
    @Query("SELECT p FROM Preparacion p WHERE p.fechaCoccion = :fechaCoccion")
    List<Preparacion> buscarPorFechaCoccion(java.time.LocalDate fechaCoccion);
    
}
