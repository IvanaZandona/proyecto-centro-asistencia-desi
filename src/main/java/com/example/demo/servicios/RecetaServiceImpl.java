package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IIngredienteRepo;
import com.example.demo.accesoDatos.IRecetaRepo;
import com.example.demo.entidades.Ingrediente;
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
		
		receta.setNombre(receta.getNombre());
		receta.setDescripcion(receta.getDescripcion());
		recetaRepo.save(receta);
		
		listarIngredientes();
		
		
		
		/*listar ingredientes
		
		cargar ese ingrediente en itemreceta
		
		cargar itemreceta en receta*/ 
		
		
				
	}
	
	public List<Ingrediente> listarIngredientes(){
		return ingredienteRepo.findAll();
	}
	
}
              