package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IPreparacionRepo;
import com.example.demo.entidades.Preparacion;
import com.example.demo.excepciones.Excepcion;

import java.util.List;

@Service
public class PreparacionServiceImpl implements PreparacionService {

	@Autowired
    private IPreparacionRepo preparacionRepo;
	
	@Override
	public List<Preparacion> getAll() {
		return preparacionRepo.findAll();
	}

	@Override
	public void save(Preparacion preparacion) throws Excepcion {
		// Validación de ejemplo: no guardar si nombre es vacío
		/*if (preparacion.getId() == null) {
			throw new Excepcion("El id es obligatorio.");
		}*/
		preparacionRepo.save(preparacion);
	}

	@Override
	public Preparacion filter(Long Id) {
		return preparacionRepo.findById(Id).orElse(null);
	}

	@Override
	public Preparacion getById(Long Id) {
		 return preparacionRepo.findById(Id).orElse(null); 
	}

	@Override
	public void deleteById(Long Id) {
		preparacionRepo.deleteById(Id);
	}

	@Override
	public List<Preparacion> findAll() {
		return preparacionRepo.findAll();
	}
	
}
