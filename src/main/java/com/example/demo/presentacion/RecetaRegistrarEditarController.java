package com.example.demo.presentacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entidades.ItemReceta;
import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.Receta;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.RecetaService;
import com.example.demo.servicios.IngredienteService;


import org.springframework.ui.Model;


@Controller //iNDICO QUE ES UN CONTROLADOR
@RequestMapping("/recetasMenu") //Mapeo la ruta base para las opercaiones de mi entidad
public class RecetaRegistrarEditarController {
	
	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String mostrarMenu(Model modelo) {
		return "recetasMenu";
	}
	
	@RequestMapping(value ="/alta",method = RequestMethod.GET) //creo el metodo para MOSTRAR el formulario de alta de receta
	public String mostrarFormularioAlta(Model modelo) {
		modelo.addAttribute("receta", new Receta()); //agrega al modelo una receta
		modelo.addAttribute("ingredientes", ingredienteService.getAll());//agrega al modelo una lista de todos los ingredientes disponibles
		Receta receta = new Receta();
		receta.setItems(new ArrayList<>());
		receta.getItems().add(new ItemReceta()); 
		modelo.addAttribute("receta", receta);
		return "recetasAlta";	
		}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST) //esto PROCESA el alta de la misma 
	public String procesarAlta(@ModelAttribute("receta") Receta receta, org.springframework.ui.Model modelo) {
	    try {
	        recetaService.save(receta);
	        // Redirigís al menú o al listado de recetas después de guardar
	        return "redirect:/recetasMenu";
	    } catch (Exception e) {
	        modelo.addAttribute("error", e.getMessage());
	        // Volvés a mostrar el formulario con el mensaje de error
	        return "recetasAlta";
	    }
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarRecetas(Model modelo) {
		List<Receta> recetas = recetaService.getAll();
		modelo.addAttribute("recetas", recetas);
		return "recetasListar";
	}
	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String mostratFormularioEditar(@PathVariable("id") Long id, Model modelo ) throws Excepcion {
		Receta receta =  recetaService.getById(id);
		modelo.addAttribute("receta", receta);
		modelo.addAttribute("ingredientes", ingredienteService.getAll());
		return "recetasEditar";
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String procesarEdicion(@ModelAttribute("receta") Receta receta, Model modelo) {
		try {
			recetaService.save(receta); //
			return "redirect:/recetasMenu/listar";
		} catch (Exception e) {
			modelo.addAttribute("errror", e.getMessage());
			modelo.addAttribute("ingredientes", ingredienteService.getAll());
			return "recetasEditar";
		}
	}
	
	@RequestMapping(value = "/eliminar/{id}", method = RequestMethod.GET)
	public String eliminarReceta(@PathVariable("id") Long id, Model modelo) {
		try {
			recetaService.deleteById(id);
			return "redirect:/recetasMenu/listar";
		} catch (Exception e) {
			modelo.addAttribute("error", e.getMessage());
			return "recetas/eliminar";
		}
	}	

}
