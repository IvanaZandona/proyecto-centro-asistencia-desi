package com.example.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.ICondimentoRepo;
import com.example.demo.accesoDatos.IIngredienteRepo;
import com.example.demo.accesoDatos.IProductoRepo;
import com.example.demo.entidades.Condimento;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.entidades.Producto;

@Service
public class IngredienteServiceImpl implements IngredienteService {
	
	@Autowired
	private IIngredienteRepo ingredienteRepo;
	
	@Autowired
	private ICondimentoRepo condimentoRepo;
	
	@Autowired
	private IProductoRepo productoRepo;
	
	

	@Override
	public List<Ingrediente> getAll(){
		return ingredienteRepo.findAll();
	}
	 
	public void saveProducto(String nombre, Integer calorias, Float stock, Float precio) throws Exception {
		if (nombre == null || nombre.isBlank() || calorias == null || stock == null || precio == null) {
	        throw new Exception("Todos los datos son obligatorios para crear un producto");
	    }
	    Producto producto = new Producto(nombre, calorias, stock, precio);
	    productoRepo.save(producto);
	}


	public void saveCondimento(String nombre, Integer calorias) throws Exception{
		if (nombre == null || nombre.isBlank() || calorias == null ) {
	        throw new Exception("Todos los datos son obligatorios para crear un producto");
	    }
	    Condimento condimento = new Condimento(nombre, calorias);
	    condimentoRepo.save(condimento);
	}

	
	
	public List<Condimento> listarCondimentos(){
		return condimentoRepo.findAll();
	}
	
	public List<Producto> listarProductos(){
		return productoRepo.findAll();
	}

	@Override
	public void save(Ingrediente ingrediente, Long idCondimento, Long idProducto) throws Exception {
		// TODO Auto-generated method stub
		
	}
		
	
}
