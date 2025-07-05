package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entidades.Asistido;
import com.example.demo.entidades.Familia;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.FamiliaService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
@RequestMapping("/familiasMenu")
@SessionAttributes("formBean")
public class FamiliaRegistrarEditarController {

	// para ver mensajes en consola
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
    public String listarFamilias(@RequestParam(value = "nroFamilia", required = false) Integer nroFamilia,
            					@RequestParam(value = "nombre", required = false) String nombre, 
            					Model modelo) {
		modelo.addAttribute("buscarFormBean", new FamiliaBuscarForm());
		
		List<Familia> familias = familiaService.buscarPorFiltros(nroFamilia, nombre);
        modelo.addAttribute("familias", familias);
        
        Map<Integer, LocalDate> mapa = familiaService.obtenerUltimaAsistenciaPorFamilia();
        if (mapa == null) {
            mapa = new HashMap<>();
        }
        modelo.addAttribute("mapaUltimaAsistencia", mapa);
        
        Map<Integer, Long> integrantesActivos = new HashMap<>();
        for (Familia familia : familias) {
            long cantidad = familia.getAsistidos().stream()
                .filter(a -> !a.isEliminado())
                .count();
            integrantesActivos.put(familia.getNroFamilia(), cantidad);
        }
        modelo.addAttribute("integrantesActivos", integrantesActivos);

        return "familiasBuscar";
    }

	@RequestMapping(value = "/editar/{nroFamilia}", method = RequestMethod.GET)
    public String preparaFormEdicion(Model modelo, @PathVariable("nroFamilia") Integer nroFamilia) {
		Familia familia = familiaService.getByNroFamilia(nroFamilia);
	    if (familia == null) {
	        modelo.addAttribute("error", "Familia no encontrada.");
	        return "redirect:/familiasMenu/listado";
	    }
	    FamiliaForm formBean = new FamiliaForm(familia);
	    modelo.addAttribute("formBean", formBean);
	    return "familiaEditar";
    }
	
	@RequestMapping(value = "/delete/{nroFamilia}", method = RequestMethod.GET)
	public String eliminarFamilia(@PathVariable("nroFamilia") Integer nroFamilia, RedirectAttributes redirectAttrs) {
	    Familia familia = familiaService.getByNroFamilia(nroFamilia);
	    if (familia != null) {
	        familia.setEliminado(true);
	        try {
	            familiaService.save(familia);
	            redirectAttrs.addFlashAttribute("mensaje", "Familia dada de baja correctamente.");
	        } catch (Excepcion e) {
	            redirectAttrs.addFlashAttribute("error", "Error al eliminar la familia: " + e.getMessage());
	        }
	    } else {
	        redirectAttrs.addFlashAttribute("error", "Familia no encontrada.");
	    }
	    return "redirect:/familiasMenu/listado";
	}

	
	@RequestMapping(value = "/agregar-integrante", method = RequestMethod.POST)
	public String agregarIntegrante(@ModelAttribute("formBean") FamiliaForm formBean,
	                                BindingResult result, ModelMap modelo) {
		
		if (result.hasErrors()) {
	        // No agregues el integrante si hay errores de validación
	        modelo.addAttribute("formBean", formBean);
	        return "familiaAlta";
	    }
		
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
	    
		logger.info("🔁 Se ejecutó submitAlta");
        logger.info("Nombre familia: {}", formBean.getNombre());
        logger.info("Cantidad integrantes: {}", formBean.getIntegrantes().size());
        	    
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

	    } catch (Excepcion e) {
	        if ("dni_duplicado".equals(e.getCodigo()) || "validacion".equals(e.getCodigo())) {
	            result.reject("globalError", e.getMessage());
	        } else {
	            result.reject("globalError", "Limpie los campos e intente nuevamente. Error inesperado al guardar: " + e.getMessage());
	        }
	        modelo.addAttribute("formBean", formBean);
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
	
	@RequestMapping(value = "/editar/agregar-integrante", method = RequestMethod.POST)
	public String agregarIntegranteEnEdicion(@ModelAttribute("formBean") FamiliaForm formBean,
	                                         BindingResult result, ModelMap modelo) {
	    
	    AsistidoForm nuevo = formBean.getNuevoIntegrante();

	    if (nuevo != null && nuevo.getDni() != null) {
	        boolean dniYaExiste = formBean.getIntegrantes().stream()
	            .anyMatch(i -> i.getDni() != null && i.getDni().equals(nuevo.getDni()));
	        
	        if (dniYaExiste) {
	            result.rejectValue("nuevoIntegrante.dni", "dni.repetido", "Este DNI ya fue ingresado.");
	            return "familiaEditar";
	        }

	        formBean.getIntegrantes().add(nuevo);
	        formBean.setNuevoIntegrante(new AsistidoForm());
	    }

	    modelo.addAttribute("formBean", formBean);
	    return "familiaEditar";
	}

	@RequestMapping(value = "/alta/limpiar", method = RequestMethod.GET)
	public String limpiarYVolverAlta(SessionStatus status) {
	    status.setComplete(); //limpiamos el atributo de sesión
	    return "redirect:/familiasMenu/alta"; // redirige al formulario vacío
	}

	
}
