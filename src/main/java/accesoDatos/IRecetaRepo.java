package accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


import entidades.Receta;

@Repository
public interface IRecetaRepo extends JpaRepository<Receta, Long> {
	
	
	List<Receta> findByNombre(String nombre);
	
	Receta findDirstbyId(Long id);
	
	
	}


