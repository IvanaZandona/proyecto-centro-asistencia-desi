package com.example.demo.presentacion;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		Receta receta = new Receta();
		receta.setItems(new ArrayList<>());
		receta.getItems().add(new ItemReceta());	
		modelo.addAttribute("receta", receta);
		modelo.addAttribute("ingredientes", ingredienteService.getAll());
		return "recetasAlta";	
		}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST, params =  "addItem")
	public String agregarIngrediente(@ModelAttribute("receta") Receta receta, Model modelo) {
		receta.getItems().add(new ItemReceta());
		modelo.addAttribute("receta", receta);
		modelo.addAttribute("ingredientes", ingredienteService.getAll());
		return "recetasAlta";
		
	}
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST, params = "saveRecipe") //esto PROCESA el alta de la misma 
	public String procesarAlta(@ModelAttribute("receta") Receta receta, Model modelo, RedirectAttributes redirectAttributes) {
	    try {
	        recetaService.save(receta);
	        redirectAttributes.addFlashAttribute("success", "Receta cargada correctamente");
	        // Redirigís al menú o al listado de recetas después de guardar
	        return "redirect:/recetasMenu";
	    } catch (Exception e) {
	        modelo.addAttribute("error", e.getMessage());
	        modelo.addAttribute("ingredientes", ingredienteService.getAll());
	        // Volvés a mostrar el formulario con el mensaje de error
	        return "recetasAlta";
	    }
	}
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listarRecetas(
	    @RequestParam(value = "minCalorias", required = false) Integer minCalorias,
	    @RequestParam(value = "maxCalorias", required = false) Integer maxCalorias,
	    Model modelo) {

	    List<Receta> recetas = recetaService.getAll();
	    if (minCalorias != null || maxCalorias != null) {
	        recetas = recetas.stream()
	            .filter(r -> {
	                int total = r.getItems().stream()
	                              .filter(ItemReceta::isActiva)
	                              .mapToInt(ItemReceta::getCalorias)
	                              .sum();
	                boolean cumpleMin = (minCalorias == null) || (total >= minCalorias);
	                boolean cumpleMax = (maxCalorias == null) || (total <= maxCalorias);
	                return cumpleMin && cumpleMax;
	            })
	            .collect(Collectors.toList());
	    }
	    modelo.addAttribute("recetas", recetas);
	    return "listadoRecetas";
	}


	
	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String mostratFormularioEditar(@PathVariable("id") Long id, Model modelo ) throws Excepcion {
	    Receta receta = recetaService.getById(id);

	    // Filtrar solo los items activos
	    receta.setItems(
	        receta.getItems().stream()
	            .filter(ItemReceta::isActiva)
	            .collect(Collectors.toList())
	    );

	    modelo.addAttribute("receta", receta);
	    modelo.addAttribute("ingredientes", ingredienteService.getAll());
	    return "recetasEditar";
	}


	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String procesarEdicion(@ModelAttribute("receta") Receta receta, Model modelo) {
		try {
			recetaService.save(receta); //
			return "redirect:/recetasMenu/listado";
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
			return "redirect:/recetasMenu/listado";
		} catch (Exception e) {
			modelo.addAttribute("error", e.getMessage());
			return "recetas/eliminar";
		}
	}
	@RequestMapping(value = "/eliminarItem/{idItem}", method = RequestMethod.GET)
	public String eliminarItemReceta(@PathVariable("idItem") Long idItem, @RequestParam("recetaId") Long recetaId, RedirectAttributes redirectAttrs) {
	    try {
	        recetaService.deleteItemReceta(idItem);
	        redirectAttrs.addFlashAttribute("success", "Ingrediente eliminado correctamente");
	    } catch (Exception e) {
	        redirectAttrs.addFlashAttribute("error", e.getMessage());
	    }
	    return "redirect:/recetasMenu/editar/" + recetaId;
	}


}
