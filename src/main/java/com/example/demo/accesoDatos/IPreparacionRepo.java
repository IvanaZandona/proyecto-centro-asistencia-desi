package com.example.demo.accesoDatos;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Preparacion;

@Repository
public interface IPreparacionRepo extends JpaRepository<Preparacion, Long> {

	// Buscar por Id de preparación
    @Query("SELECT p FROM Preparacion p WHERE p.id = :idpreparacion and p.activo = true")
    Preparacion findByIdActivo(Long idpreparacion);

    // Buscar por fecha exacta de cocción
    /*@Query("SELECT p FROM Preparacion p WHERE p.fechaCoccion = :fechaCoccion and p.activo = true")
    List<Preparacion> buscarPorFechaCoccion(java.time.LocalDate fechaCoccion);*/

    @Query("SELECT p FROM Preparacion p WHERE p.fechaCoccion=:fecha_coccion and p.activo = true")
	List<Preparacion> findByFecha(LocalDate fecha_coccion);

    @Query("SELECT p FROM Preparacion p WHERE p.receta.id=:idreceta and p.fechaCoccion=:fecha_coccion and p.activo = true")
	List<Preparacion> findByFechaReceta(LocalDate fecha_coccion, Long idreceta);
    
    @Query("SELECT p FROM Preparacion p WHERE p.receta.id=:idreceta and p.activo = true")
	List<Preparacion> findByRecetaActivo(Long idreceta);

    @Query("SELECT p FROM Preparacion p WHERE p.receta.id=:idreceta")
	List<Preparacion> findByReceta(Long idreceta);

    @Query("SELECT p FROM Preparacion p WHERE p.activo = true")
    List<Preparacion> findAllActivo();

    @Override
    default void deleteById(Long id) {
        Preparacion preparacion = findById(id).orElse(null);
        if (preparacion != null) {
            preparacion.setActivo(false); // Cambiar el estado a inactivo
            save(preparacion); // Guardar el cambio
        }
    }
}
