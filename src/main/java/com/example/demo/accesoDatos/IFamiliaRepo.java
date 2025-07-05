package com.example.demo.accesoDatos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Familia;

@Repository
public interface IFamiliaRepo extends JpaRepository<Familia, Integer> {

    // Combinar búsqueda por nombre y fecha
   // @Query("SELECT f FROM Familia f WHERE f.nombre LIKE %:nombre% AND f.fechaRegistro = :fechaRegistro")
   // List<Familia> buscarPorNombreYFecha(String nombre, java.time.LocalDate fechaRegistro);

	//List<Familia> findByNombreContaining(String nombre);
    
	Optional<Familia> findByNroFamilia(Integer nroFamilia); // Para filtros

	List<Familia> findByNombreContainingIgnoreCase(String nombre); // Para búsquedas por nombre
	
	//ultima fecha de asistencia recibida
	@Query("""
		    SELECT f.nroFamilia, MAX(ea.fecha)
		    FROM Familia f
		    LEFT JOIN EntregaAsistencia ea ON ea.familia.nroFamilia = f.nroFamilia
		    GROUP BY f.nroFamilia
		""")
	List<Object[]> obtenerFechaUltimaAsistenciaPorFamilia();

	@Query("SELECT DISTINCT f FROM Familia f LEFT JOIN FETCH f.asistidos a LEFT JOIN FETCH a.familia")
	List<Familia> findAllConAsistidos();

	@Query("SELECT DISTINCT f FROM Familia f LEFT JOIN FETCH f.asistidos a WHERE LOWER(f.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
	List<Familia> buscarConAsistidosPorNombre(@Param("nombre") String nombre);

	@Query("SELECT f FROM Familia f LEFT JOIN FETCH f.asistidos WHERE f.nroFamilia = :nroFamilia")
	Optional<Familia> buscarConAsistidosPorNro(@Param("nroFamilia") Integer nroFamilia);


}
