package presentacion;

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
import org.springframework.web.bind.annotation.RequestParam;

import entidades.Familia;
import jakarta.validation.Valid;
import servicios.FamiliaService;

@Controller
@RequestMapping("/familiasBuscar")
public class FamiliaBuscarController {

	@Autowired
	private FamiliaService familiaService;
	
	@GetMapping
	public String preparaForm(Model modelo) {
		FamiliaBuscarForm form = new FamiliaBuscarForm();
		modelo.addAttribute("formBean", form);
		modelo.addAttribute("familias", familiaService.getAll());
		return "familiasBuscar"; //nombre del html
	}
	
	//@GetMapping("/familias")
	public String listar(Model modelo) {
	    modelo.addAttribute("familias", familiaService.getAll());
	    return "familiasBuscar"; //tu HTML
	}

	@PostMapping
	public String submit(@ModelAttribute("formBean") @Valid FamiliaBuscarForm formBean, BindingResult result, 
						 ModelMap modelo, @RequestParam String action) {
		if (action.equals("actionBuscar")) {
			try {
				List<Familia> familias = familiaService.filter(formBean.getNombre());
				modelo.addAttribute("resultados", familias);
			} catch (Exception e) {
				result.addError(new ObjectError("globalError", e.getMessage()));
			}
			return "familiaBuscar";
		} else if (action.equals("actionRegistrar")) {
			return "redirect:/familiaEditar";
		} else if (action.equals("actionCancelar")) {
			return "redirect:/";
		}
		return "redirect:/";
	}
	
}
