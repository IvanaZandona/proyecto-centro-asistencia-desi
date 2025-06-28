package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IIngredienteRepo;
import com.example.demo.accesoDatos.IRecetaRepo;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.ItemReceta;
import com.example.demo.entidades.Receta;

@Service
public class RecetaServiceImpl implements RecetaService{
	
	
	@Autowired
	private IRecetaRepo recetaRepo;
	
	@Autowired
	private IIngredienteRepo ingredienteRepo;
	

	
	
	@Override
	public List<Receta> getAll(){
		return recetaRepo.findAll();
	}
	@Override
	public void save(Receta receta) throws Exception{
		
		if (receta.getNombre() == null || receta.getNombre().isBlank() || recetaRepo.existsByNombre(receta.getNombre())) {
			throw new Exception("El nombre de la receta es obligatorio y no se debe repetir");
		}
		if (receta.getDescripcion() == null || receta.getDescripcion().isBlank()) {
			throw new Exception("La descripcion de la receta es obligatoria");
		}
		if(receta.getItems() == null || receta.getItems().isEmpty()) {
			throw new Exception("La receta dbe tener al menos un ingrediente");
		}
		
		for (ItemReceta item : receta.getItems()) {
	        if (item.getIngrediente() == null || item.getIngrediente().getId() == null) {
	            throw new Exception("Cada item debe tener un ingrediente válido");
	        }
	        if (item.getCantidad() == null || item.getCantidad() <= 0) {
	            throw new Exception("La cantidad debe ser mayor a cero");
	        }
	        if (item.getCalorias() == null || item.getCalorias() < 0) {
	            throw new Exception("Las calorías no pueden ser negativas");
	        }
	        // Setear la receta en el item para la relación bidireccional
	        item.setReceta(receta);
	    }
		
		receta.setNombre(receta.getNombre());
		receta.setDescripcion(receta.getDescripcion());
		recetaRepo.save(receta);
				
	}
	
	public List<Ingrediente> listarIngredientes(){
		return ingredienteRepo.findAll();
	}
	
}
              