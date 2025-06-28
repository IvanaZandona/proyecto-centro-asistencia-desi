package com.example.demo.presentacion;

import java.util.List;

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

import com.example.demo.entidades.Familia;
import com.example.demo.servicios.FamiliaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/familiasBuscar")
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
		modelo.addAttribute("formBean", form);
		modelo.addAttribute("familias", familiaService.getAll()); // muestra todo inicialmente
		return "familiasBuscar";
	}
	
	//@GetMapping("/familias")
	/*public String listar(Model modelo) {
	    modelo.addAttribute("familias", familiaService.getAll());
	    return "familiasBuscar"; //tu HTML
	}*/

	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("formBean") @Valid FamiliaBuscarForm formBean, BindingResult result,
						 ModelMap modelo, @RequestParam String action) {

		if (action.equals("actionBuscar")) {
			try {
				List<Familia> familias = familiaService.filter(formBean.getNombre());
				modelo.addAttribute("familias", familias); // reemplaza resultados por familias
			} catch (Exception e) {
				result.addError(new ObjectError("globalError", e.getMessage()));
			}
			modelo.addAttribute("formBean", formBean);
			return "familiasBuscar";
		}
		else if (action.equals("actionRegistrar")) {
			return "redirect:/familiaEditar"; // aún no creado
		}
		else if (action.equals("actionCancelar")) {
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	
}
