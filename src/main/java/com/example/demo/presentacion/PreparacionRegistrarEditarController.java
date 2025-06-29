package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.List;
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
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.PreparacionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/preparacionesMenu")
public class PreparacionRegistrarEditarController {

	@Autowired
	private PreparacionService preparacionService;

	@RequestMapping(method = RequestMethod.GET)
    public String mostrarMenu(Model modelo) {
        return "preparacionesMenu";
    }
		
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String preparaFormAlta(Model modelo) {
		modelo.addAttribute("formBean", new PreparacionForm());
	    return "preparacionesAlta";
	 }
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
    public String listarPreparaciones(Model modelo) {
        List<Preparacion> preparaciones = preparacionService.findAll();
        modelo.addAttribute("formBean", new PreparacionBuscarForm());
        modelo.addAttribute("preparaciones", preparaciones);
        return "listadoPreparaciones";
    }

	@RequestMapping(value = "/editar/{Id}", method = RequestMethod.GET)
    public String preparaFormEdicion(Model modelo, @PathVariable("Id") Long Id) {
        Preparacion preparacion = preparacionService.getById(Id);
        modelo.addAttribute("formBean", new PreparacionForm(preparacion));
        return "preparacionesEditar";
    }
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
    public String delete(@PathVariable("Id") Long Id) {
        preparacionService.deleteById(Id);
        return "redirect:/preparacionesMenu/listado";
    }
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
    public String submitAlta(@ModelAttribute("formBean") @Valid PreparacionForm formBean,
                              BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "preparacionesAlta";
        } else {
            try {
                Preparacion preparacion = formBean.toPojo();
                preparacion.setFechaCoccion(LocalDate.now());
                preparacionService.save(preparacion);
                return "redirect:/preparacionesMenu/listado";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "preparacionesAlta";
            }
        }
    }
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String submitEdicion(@ModelAttribute("formBean") @Valid PreparacionForm formBean,
                                 BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "preparacionesEditar";
        } else {
            try {
                preparacionService.save(formBean.toPojo());
                return "redirect:/preparacionesMenu/listado";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "preparacionesEditar";
            }
        }
    }
	
	
}
