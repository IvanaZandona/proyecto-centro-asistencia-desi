package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.entidades.Asistido;
import com.example.demo.entidades.Familia;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.FamiliaService;

import jakarta.validation.Valid;

//temporal
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/familiasMenu")
@SessionAttributes("formBean")
public class FamiliaRegistrarEditarController {

	//temporal para ver mensajes en consola
	 private static final Logger logger = LoggerFactory.getLogger(FamiliaRegistrarEditarController.class);
	
	@Autowired
	private FamiliaService familiaService;

	@ModelAttribute("formBean")
	public FamiliaForm crearFormBean() {
	    FamiliaForm form = new FamiliaForm();
	    form.setIntegrantes(new ArrayList<>());
	    form.setNuevoIntegrante(new AsistidoForm());
	    return form;
	}
	
	@RequestMapping(method = RequestMethod.GET)
    public String mostrarMenu(Model modelo) {
        return "familiasMenu";
    }
	
	@RequestMapping(value = "/alta", method = RequestMethod.GET)
	public String preparaFormAlta(@ModelAttribute("formBean") FamiliaForm formBean, Model modelo) {
		if (formBean.getIntegrantes() == null) {
	        formBean.setIntegrantes(new ArrayList<>());
	    }
	    if (formBean.getIntegrantes().isEmpty()) {
	        // Solo agregamos un integrante vacío si la lista está vacía (para evitar mostrar integrante vacío)
	        // limpia el nuevoIntegrante para formulario "agregar integrante"
	        formBean.setNuevoIntegrante(new AsistidoForm());
	    }
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
	
	@RequestMapping(value = "/agregar-integrante", method = RequestMethod.POST)
	public String agregarIntegrante(@ModelAttribute("formBean") FamiliaForm formBean,
	                                BindingResult result, ModelMap modelo) {
	    // Validación manual de campos del nuevo integrante 
	    AsistidoForm nuevo = formBean.getNuevoIntegrante();
	    if (nuevo != null && nuevo.getDni() != null) {
	        // Validar que el DNI no esté repetido en la lista actual
	        boolean dniYaExiste = formBean.getIntegrantes().stream()
	            .anyMatch(i -> i.getDni() != null && i.getDni().equals(nuevo.getDni()));

	        if (dniYaExiste) {
	            result.rejectValue("nuevoIntegrante.dni", "dni.repetido", "Este DNI ya fue ingresado.");
	            return "familiaAlta";
	        }
	        //agregamos y limpiamos
	        formBean.getIntegrantes().add(nuevo);
	        formBean.setNuevoIntegrante(new AsistidoForm());
	    }
	    modelo.addAttribute("formBean", formBean);
	    return "familiaAlta";
	}

	
	@RequestMapping(value = "/alta", method = RequestMethod.POST)
	public String submitAlta(@ModelAttribute("formBean") @Valid FamiliaForm formBean,
	                         BindingResult result, ModelMap modelo, SessionStatus status) {
	    
		//temporallllllllll
		logger.info("🔁 Se ejecutó submitAlta");
        logger.info("Nombre familia: {}", formBean.getNombre());
        logger.info("Cantidad integrantes: {}", formBean.getIntegrantes().size());
        
		
		if (result.hasErrors()) {
	        return "familiaAlta";
	    }
	    
	    try {
	        Familia familia = formBean.toPojo();
	        familia.setFechaRegistro(LocalDate.now());

	        System.out.println(">>> Guardando familia: " + familia.getNombre());
	        System.out.println(">>> Cantidad de integrantes: " + familia.getAsistidos().size());

	        for (Asistido a : familia.getAsistidos()) {
	            System.out.println(">>> Integrante: " + a.getDni() + " - " + a.getNombre());
	        }

	        familiaService.save(familia);

	        status.setComplete(); // borra formBean de sesión
	        return "redirect:/familiasMenu/listado";

	    } catch (Exception e) {
	        e.printStackTrace(); // muestra error completo en consola
	        result.addError(new ObjectError("globalError", "Error al guardar: " + e.getMessage()));
	        return "familiaAlta";
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
