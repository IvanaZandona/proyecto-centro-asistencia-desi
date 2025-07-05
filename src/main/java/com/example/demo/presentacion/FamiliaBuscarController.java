package com.example.demo.presentacion;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entidades.Familia;
import com.example.demo.servicios.FamiliaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/familiasBuscar")
@SessionAttributes("buscarFormBean") 
public class FamiliaBuscarController {

	@Autowired
	private FamiliaService familiaService;
	
	@GetMapping("/")
	public String mostrarIndex() {
	    return "index"; // Renderiza src/main/resources/templates/index.html
	}

	@RequestMapping(method = RequestMethod.GET)
	public String preparaForm(Model modelo) {
		FamiliaBuscarForm form = new FamiliaBuscarForm();
	    modelo.addAttribute("buscarFormBean", form);

	    // Traer familias con asistidos
	    List<Familia> familias = familiaService.buscarPorFiltros(null, null);
	    modelo.addAttribute("familias", familias);

	    // Agregar mapa de ultima asistencia
	    Map<Integer, LocalDate> mapa = familiaService.obtenerUltimaAsistenciaPorFamilia();
	    if (mapa == null) {
	        mapa = new HashMap<>();
	    }
	    modelo.addAttribute("mapaUltimaAsistencia", mapa);

	    // Calcular integrantes activos
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
	

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("buscarFormBean") @Valid FamiliaBuscarForm formBean,
						 BindingResult result,
						 ModelMap modelo,
						 @RequestParam String action,
						 SessionStatus status) {

		switch (action) {
        case "actionBuscar":
            try {
                List<Familia> familias = familiaService.buscarPorFiltros(formBean.getNroFamilia(), formBean.getNombre());
                modelo.addAttribute("familias", familias);
            } catch (Exception e) {
                result.addError(new ObjectError("globalError", "Error al buscar: " + e.getMessage()));
            }
            modelo.addAttribute("buscarFormBean", formBean);
            return "familiasBuscar";

        case "actionRegistrar":
            return "redirect:/familiasMenu/alta";

        case "actionCancelar":
        	status.setComplete();
        	return "redirect:/familiasBuscar";
        	
        default:
            return "redirect:/familiasBuscar";
		}
	}
}
