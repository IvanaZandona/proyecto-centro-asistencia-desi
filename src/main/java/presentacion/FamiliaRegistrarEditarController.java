package presentacion;

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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import entidades.Familia;
import excepciones.Excepcion;
import servicios.FamiliaService;

@Controller
@RequestMapping("/familiaEditar")
public class FamiliaRegistrarEditarController {

	@Autowired
	private FamiliaService familiaService;

	@GetMapping({"", "/{nroFamilia}"})
	public String preparaForm(Model modelo, @PathVariable(name = "nroFamilia", required = false) Optional<Integer> nroFamilia) {
		if (nroFamilia.isPresent()) {
			Familia f = familiaService.getByNroFamilia(nroFamilia.get());
			modelo.addAttribute("formBean", new FamiliaForm(f));
		} else {
			modelo.addAttribute("formBean", new FamiliaForm());
		}
		return "familiaEditar";
	}

	@GetMapping("/delete/{nroFamilia}")
	public String delete(@PathVariable("nroFamilia") Integer nroFamilia) {
		familiaService.deleteByNroFamilia(nroFamilia);
		return "redirect:/familiaBuscar";
	}

	@PostMapping
	public String submit(@ModelAttribute("formBean") @Valid FamiliaForm formBean, BindingResult result, 
						 ModelMap modelo, @RequestParam String action) {
		if (action.equals("actionAceptar")) {
			if (result.hasErrors()) {
				modelo.addAttribute("formBean", formBean);
				return "familiaEditar";
			} else {
				try {
					familiaService.save(formBean.toPojo());
					return "redirect:/familiaBuscar";
				} catch (Excepcion e) {
					if (e.getAtributo() == null) {
						result.addError(new ObjectError("globalError", e.getMessage()));
					} else {
						result.addError(new FieldError("formBean", e.getAtributo(), e.getMessage()));
					}
					modelo.addAttribute("formBean", formBean);
					return "familiaEditar";
				}
			}
		} else if (action.equals("actionCancelar")) {
			return "redirect:/familiaBuscar";
		}
		return "redirect:/";
	}
	
}
