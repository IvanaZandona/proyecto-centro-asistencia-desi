package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entidades.Receta;
import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.ItemReceta;
import com.example.demo.entidades.Ingrediente;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.PreparacionService;
import com.example.demo.servicios.RecetaService;
import com.example.demo.servicios.IngredienteService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/preparacionesMenu")
public class PreparacionRegistrarEditarController {

	@Autowired
	private PreparacionService preparacionService;
	
	@Autowired
	private RecetaService recetaService;
	
	@Autowired
	private IngredienteService ingredienteService;

	@RequestMapping(method = RequestMethod.GET)
    public String mostrarMenu(Model modelo) {
        return "preparacionesMenu";
    }
		
	@ModelAttribute("allRecetas")
    public List<Receta> getAllRecetas() {
        return this.recetaService.getAll();
    }
	
	
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String preparaFormAlta(Model modelo) {
		modelo.addAttribute("formBean", new PreparacionForm());
		modelo.addAttribute("recetas", recetaService.getAll());
	    return "preparacionesEditar";
	 }
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
    public String listarPreparaciones(Model modelo) {
        List<Preparacion> preparaciones = preparacionService.getAll(true);
        modelo.addAttribute("formBean", new PreparacionBuscarForm());
        modelo.addAttribute("preparaciones", preparaciones);
        return "preparacionesBuscar";
    }

	@RequestMapping(value = "/editar/{Id}", method = RequestMethod.GET)
    public String preparaFormEdicion(Model modelo, @PathVariable("Id") Long Id) {
        Preparacion preparacion = preparacionService.getById(Id);
        modelo.addAttribute("recetas", recetaService.getAll());
        modelo.addAttribute("formBean", new PreparacionForm(preparacion));
        return "preparacionesEditar";
    }
	
	@RequestMapping(value = "/eliminar/{Id}", method = RequestMethod.GET)
    public String delete(@PathVariable("Id") Long Id) {
        preparacionService.deleteById(Id);
        return "redirect:/preparacionesBuscar";
    }
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
    public String submitAlta(@ModelAttribute("formBean") @Valid PreparacionForm formBean,
                              BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            modelo.addAttribute("recetas", recetaService.getAll());
            modelo.addAttribute("errorMsg", result.getFieldError().getDefaultMessage());
            return "preparacionesEditar";
        } else {
            try {
                Preparacion preparacion = formBean.toPojo();
                System.out.println("IdReceta: " + formBean.getIdreceta());
                preparacion.setReceta(recetaService.getById(formBean.getIdreceta()));
                preparacion.setActivo(true);
                preparacion.setStockRacionesRestantes(preparacion.getTotalRacionesPreparadas());
                preparacionService.save(preparacion);
                return "redirect:/preparacionesBuscar";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                modelo.addAttribute("recetas", recetaService.getAll());
                modelo.addAttribute("errorMsg", e.getMessage());
                return "preparacionesEditar";
            } catch (Exception e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                modelo.addAttribute("recetas", recetaService.getAll());
                modelo.addAttribute("errorMsg", e.getMessage());
                return "preparacionesEditar";
            }
        }
    }
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String submitEdicion(@ModelAttribute("formBean") @Valid PreparacionForm formBean,
                                 BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            modelo.addAttribute("recetas", recetaService.getAll());
            return "preparacionesEditar";
        } else {
            try {
                Preparacion preparacion = formBean.toPojo();
                preparacion.setReceta(recetaService.getById(formBean.getIdreceta()));
                preparacionService.save(preparacion);
                return "redirect:/preparacionesBuscar";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                modelo.addAttribute("recetas", recetaService.getAll());
                modelo.addAttribute("errorMsg", e.getMessage());
                return "preparacionesEditar";
            } catch (Exception e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                modelo.addAttribute("recetas", recetaService.getAll());
                modelo.addAttribute("errorMsg", e.getMessage());
                return "preparacionesEditar";
            }
        }
    }
	
	
}
