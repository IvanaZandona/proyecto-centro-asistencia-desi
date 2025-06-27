package presentacion;

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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import entidades.Familia;
import entidades.Asistido;
import excepciones.Excepcion;
import servicios.FamiliaService;

@Controller
@RequestMapping("/familiasMenu")
public class FamiliaRegistrarEditarController {

	@Autowired
	private FamiliaService familiaService;

	@GetMapping
	public String mostrarMenu(Model modelo) {
	    return "familiasMenu"; // Nombre del archivo HTML sin la extensión
	}
	
	@GetMapping("/alta")
    public String preparaForm(Model modelo) {
        modelo.addAttribute("formBean", new FamiliaForm());
        return "familiaAlta"; // Nombre de la vista para el formulario de alta
    }
	
	@GetMapping("/listado")
	public String listarFamilias(Model modelo) {
	    List<Familia> familias = familiaService.findAll();
	    modelo.addAttribute("familias", familias);
	    return "listadoFamilias"; // Nombre de la vista para listar familias
	}

	@GetMapping("/editar/{nroFamilia}")
	public String preparaFormEdicion(Model modelo, @PathVariable("id") Integer id) {
	    Familia familia = familiaService.getByNroFamilia(id);
	    modelo.addAttribute("formBean", new FamiliaForm(familia));
	    return "familiaEditar"; // Nombre de la vista para editar
	}
	
	@GetMapping("/delete/{nroFamilia}")
    public String delete(@PathVariable("nroFamilia") Integer nroFamilia) {
        familiaService.deleteByNroFamilia(nroFamilia);
        return "redirect:/familiasMenu/listadoFamilias"; // Redirigir a la lista de familias
    }
	
	@PostMapping("/alta")
    public String submitAlta(@ModelAttribute("formBean") @Valid FamiliaForm formBean, BindingResult result, 
                             ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "familiaAlta"; // Volver al formulario si hay errores
        } else {
            try {
                Familia familia = formBean.toPojo();
                familia.setFechaRegistro(LocalDate.now()); // Establecer la fecha de alta
                familiaService.save(familia);
                return "redirect:/familia/listado"; // Redirigir a la lista de familias
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "familiaAlta"; // Volver al formulario si hay un error
            }
        }
    }
	
	@PostMapping("/editar")
    public String submitEdicion(@ModelAttribute("formBean") @Valid FamiliaForm formBean, BindingResult result, 
                                 ModelMap modelo) {
        if (result.hasErrors()) {
            modelo.addAttribute("formBean", formBean);
            return "familiaEditar"; // Volver al formulario si hay errores
        } else {
            try {
                familiaService.save(formBean.toPojo());
                return "redirect:/familia/listado"; // Redirigir a la lista de familias
            } catch (Excepcion e) {
                result.addError(new ObjectError("globalError", e.getMessage()));
                modelo.addAttribute("formBean", formBean);
                return "familiaEditar"; // Volver al formulario si hay un error
            }
        }
    }
	
	
}
