package servicios;

import java.util.List;

import entidades.Familia;
import excepciones.Excepcion;

public interface FamiliaService {

	
	// Obtiene todas las familias.
	List<Familia> getAll();

	
	//Obtiene una familia por ID (nroFamilia).
	Familia getById(Integer id);

	
	//Guarda o actualiza una familia.
	void save(Familia familia) throws Excepcion;

	// Elimina una familia por ID.
	void deleteById(Integer id);


	List<Familia> filter(String nombre); //REVISARRRRRR


	Familia getByNroFamilia(Integer integer); //REVISARRRRRR


	void deleteByNroFamilia(Integer nroFamilia); //REVISARRRRRRRRRR


	List<Familia> findAll(); //REVISAR
    
}
