package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.accesoDatos.IFamiliaRepo;
import com.example.demo.entidades.Familia;
import com.example.demo.entidades.Asistido;
import com.example.demo.excepciones.Excepcion;

//para forzar conexion
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FamiliaServiceImpl implements FamiliaService {

	@Autowired
    private IFamiliaRepo familiaRepo;
	
	//para forzar el insert
	@Autowired
	private EntityManager em;
	
	@Override
	public List<Familia> getAll() {
		return familiaRepo.findAll();
	}

	@Override
	@Transactional
	public void save(Familia familia) throws Excepcion {
		// Validación de ejemplo: no guardar si nombre es vacío
		if (familia.getNombre() == null || familia.getNombre().isBlank()) {
			throw new Excepcion("El nombre de la familia es obligatorio.");
		}
	    System.out.println("Guardando familia: " + familia.getNombre() + " con " + familia.getAsistidos().size() + " integrantes.");
		familiaRepo.save(familia);
	    em.flush(); // Fuerza sincronizar con la BD, debería generar los inserts en consola
	}
	
	@Override
	public List<Familia> filter(String nombre) {
		//return familiaRepo.buscarPorNombre(nombre);
		return familiaRepo.findByNombreContaining(nombre);
	}

	@Override
	public Familia getByNroFamilia(Integer nroFamilia) {
		 return familiaRepo.findById(nroFamilia).orElse(null); 
	}

	@Override
	public void deleteByNroFamilia(Integer nroFamilia) {
		familiaRepo.deleteById(nroFamilia);
	}

	@Override
	public List<Familia> findAll() {
		return familiaRepo.findAll();
	}
	
}
