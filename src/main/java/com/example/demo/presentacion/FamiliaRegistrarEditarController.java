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

import com.example.demo.entidades.Asistido;
import com.example.demo.entidades.Familia;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.FamiliaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/familiasMenu")
public class FamiliaRegistrarEditarController {

	@Autowired
	private FamiliaService familiaService;

	@RequestMapping(method = RequestMethod.GET)
    public String mostrarMenu(Model modelo) {
        return "familiasMenu";
    }
	
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String preparaFormAlta(Model modelo) {
		modelo.addAttribute("formBean", new FamiliaForm());
	    return "familiaAlta";
	 }
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
    public String listarFamilias(Model modelo) {
        List<Familia> familias = familiaService.findAll();
        modelo.addAttribute("familias", familias);
        return "familiasBuscar";
    }

	@RequestMapping(value = "/editar/{nroFamilia}", method = RequestMethod.GET)
    public String preparaFormEdicion(Model modelo, @PathVariable("nroFamilia") Integer nroFamilia) {
        Familia familia = familiaService.getByNroFamilia(nroFamilia);
        modelo.addAttribute("formBean", new FamiliaForm(familia));
        return "familiaEditar";
    }
	
	@RequestMapping(value = "/delete/{nroFamilia}", method = RequestMethod.GET)
    public String delete(@PathVariable("nroFamilia") Integer nroFamilia) {
        familiaService.deleteByNroFamilia(nroFamilia);
        return "redirect:/familiasMenu/listado";
    }
	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
    public String submitAlta(@ModelAttribute("formBean") @Valid FamiliaForm formBean,
                              BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "familiaAlta";
        } else {
            try {
                Familia familia = formBean.toPojo();
                familia.setFechaRegistro(LocalDate.now());
                familiaService.save(familia);
                return "redirect:/familiasMenu/listado";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "familiaAlta";
            }
        }
    }
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
    public String submitEdicion(@ModelAttribute("formBean") @Valid FamiliaForm formBean,
                                 BindingResult result, ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "familiaEditar";
        } else {
            try {
                familiaService.save(formBean.toPojo());
                return "redirect:/familiasMenu/listado";
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "familiaEditar";
            }
        }
    }
	
	
}
