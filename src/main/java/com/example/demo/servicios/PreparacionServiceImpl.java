package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IPreparacionRepo;
import com.example.demo.entidades.Preparacion;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.presentacion.PreparacionBuscarForm;

import java.util.List;

@Service
public class PreparacionServiceImpl implements PreparacionService {

	@Autowired
    private IPreparacionRepo preparacionRepo;
	
	@Override
	public List<Preparacion> getAll() {
		return getAll(true);
	}

	@Override
	public List<Preparacion> getAll(boolean estaActivo) {
		if (estaActivo) {
			return preparacionRepo.findAllActivo();
		}
		return preparacionRepo.findAll();
	}

	@Override
	public void save(Preparacion preparacion) throws Excepcion {
		preparacionRepo.save(preparacion);
	}

	/*@Override
	public Preparacion filter(Long Id) {
		return preparacionRepo.findById(Id).orElse(null);
	}*/

	@Override 
	public List<Preparacion> filter(PreparacionBuscarForm filter) throws Excepcion {
		if (filter.getId() == null && filter.getRecetaSeleccionada() == null) {
			throw new Excepcion("Es necesario al menos un filtro");
		} else {
			if (filter.getId() != null) {
				if (filter.getSoloActivo() == true) {
					return List.of(preparacionRepo.findByIdActivo(filter.getId()));
				} else {
					return List.of(preparacionRepo.findById(filter.getId()).orElse(null));
				}
			} else {
				if (filter.getSoloActivo() == true) {
					return preparacionRepo.findByRecetaActivo(filter.getRecetaSeleccionada());
				} else {
					return preparacionRepo.findByReceta(filter.getRecetaSeleccionada());
				}
			}
		}
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
