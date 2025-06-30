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

import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.Receta;
import com.example.demo.servicios.PreparacionService;
import com.example.demo.servicios.RecetaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/preparacionesBuscar")
public class PreparacionBuscarController {

	@Autowired
	private PreparacionService preparacionService;
	
	@Autowired
	private RecetaService recetaService;
	/*@GetMapping("/")
	public String mostrarIndex() {
	    return "index"; // Renderiza src/main/resources/templates/index.html
	}*/

	@RequestMapping(method = RequestMethod.GET)
	public String preparacionForm(Model modelo) {
		PreparacionBuscarForm form = new PreparacionBuscarForm();
		form.setRecetas(recetaService.getAll());
		modelo.addAttribute("formBean", form);
		modelo.addAttribute("preparaciones", preparacionService.getAll()); // muestra todo inicialmente
		return "preparacionesBuscar";
	}
	
	@ModelAttribute("allRecetas")
    public List<Receta> getAllRecetas() {
        return this.recetaService.getAll();
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@ModelAttribute("formBean") @Valid PreparacionBuscarForm formBean, BindingResult result,
						 ModelMap modelo, @RequestParam String action) {

		if (action.equals("actionBuscar")) {
			try {
				List<Preparacion> preparaciones = preparacionService.filter(formBean);
				modelo.addAttribute("preparaciones", preparaciones); // reemplaza resultados por preparacion
			} catch (Exception e) {
				result.addError(new ObjectError("globalError", e.getMessage()));
			}
			modelo.addAttribute("formBean", formBean);
			return "preparacionesBuscar";
		}
		else if (action.equals("actionRegistrar")) {
			return "redirect:/preparacionEditar"; 
		}
		else if (action.equals("actionCancelar")) {
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	
}
