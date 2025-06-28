package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IIngredienteRepo;
import com.example.demo.entidades.Ingrediente;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
	private IIngredienteRepo ingredienteRepo;
	

	@Override
	public List<Ingrediente> getAll(){
		return ingredienteRepo.findAll();
	}
	
	@Override 
	public void save(Ingrediente ingrediente) throws Exception{
		if(ingrediente.getNombre() == null || ingrediente.getNombre().isBlank()) {
			throw new Exception ("Para crear un ingrediente, todos los datos deben estar completos");
		}
		
		ingrediente.setNombre(ingrediente.getNombre());
		ingrediente.setCalorias(ingrediente.getCalorias());
		ingrediente.setCantidad(ingrediente.getCantidad());
		
		ingredienteRepo.save(ingrediente);
		
		
	}
	
	
	
	
}
