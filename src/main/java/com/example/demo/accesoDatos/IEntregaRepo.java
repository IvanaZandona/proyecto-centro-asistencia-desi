package com.example.demo.accesoDatos;

import com.example.demo.entidades.Familia;
import com.example.demo.entidades.EntregaAsistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEntregaRepo extends JpaRepository<EntregaAsistencia, Long> {

    List<EntregaAsistencia> findByFecha(LocalDate fecha);
    
    Optional<EntregaAsistencia> findByFechaAndFamilia(LocalDate fecha, Familia familia);
    
    List<EntregaAsistencia> findByFechaAndFamilia_NroFamilia(LocalDate fecha, Integer nroFamilia);
    
    List<EntregaAsistencia> findByFechaAndFamilia_NombreContainingIgnoreCase(LocalDate fecha, String nombreFamilia);
}

