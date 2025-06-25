package accesoDatos;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import entidades.Familia;

@Repository
public interface IFamiliaRepo extends JpaRepository<Familia, Integer> {

	// Buscar familias por nombre aproximado (like)
    @Query("SELECT f FROM Familia f WHERE f.nombre LIKE %:nombre%")
    List<Familia> buscarPorNombre(String nombre);

    // Buscar por fecha exacta de registro
    @Query("SELECT f FROM Familia f WHERE f.fechaRegistro = :fechaRegistro")
    List<Familia> buscarPorFechaRegistro(java.time.LocalDate fechaRegistro);

    // Combinar búsqueda por nombre y fecha
    @Query("SELECT f FROM Familia f WHERE f.nombre LIKE %:nombre% AND f.fechaRegistro = :fechaRegistro")
    List<Familia> buscarPorNombreYFecha(String nombre, java.time.LocalDate fechaRegistro);
    
}
