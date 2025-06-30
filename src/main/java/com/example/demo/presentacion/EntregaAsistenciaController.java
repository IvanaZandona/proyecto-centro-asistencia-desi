package com.example.demo.presentacion;

import com.example.demo.entidades.EntregaAsistencia;
import com.example.demo.entidades.Familia;
import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.Voluntario;
import com.example.demo.excepciones.Excepcion;
import com.example.demo.servicios.EntregaAsistenciaService;
import com.example.demo.servicios.FamiliaService;
import com.example.demo.servicios.PreparacionService;
import com.example.demo.servicios.VoluntarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*; // Importa todo lo necesario
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/entregaAsistenciaMenu") // <-- CAMBIO AQUÍ: Ahora este es el base path para todas las operaciones de Entrega
public class EntregaAsistenciaController { // Puedes renombrarlo a EntregaAsistenciaGestionController si prefieres, pero EntregaAsistenciaController es común

    @Autowired
    private EntregaAsistenciaService entregaAsistenciaService;

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private PreparacionService preparacionService;

    @Autowired
    private VoluntarioService voluntarioService;

    // --- Muestra el menú principal de Entregas (equivalente al RequestMapping base del de Familias)
    @RequestMapping(method = RequestMethod.GET) // Maneja GET /entregasAsistenciaMenu
    public String mostrarMenu(Model modelo) {
        return "entregaAsistenciaMenu"; // Tu HTML para el menú de entregas
    }

    // --- HISTORIA 2: REGISTRAR ENTREGA (Anteriormente /entregasAsistencia/alta) ---

    @GetMapping("/alta") // Maneja GET /entregasAsistenciaMenu/alta
    public String mostrarFormularioAltaEntrega(Model model) {
        model.addAttribute("entregaForm", new EntregaAsistenciaForm());
        model.addAttribute("familias", familiaService.findAll());
        model.addAttribute("preparaciones", preparacionService.findAll());
        model.addAttribute("voluntarios", voluntarioService.findAll());
        return "entregaAsistenciaAlta"; // Tu HTML para el formulario de alta
    }

    @PostMapping("/alta") // Maneja POST /entregasAsistenciaMenu/alta
    public String procesarFormularioAltaEntrega(@ModelAttribute("entregaForm") @Valid EntregaAsistenciaForm entregaForm,
                                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("familias", familiaService.findAll());
            model.addAttribute("preparaciones", preparacionService.findAll());
            model.addAttribute("voluntarios", voluntarioService.findAll());
            return "entregaAsistenciaAlta";
        }

        try {
            Familia familia = familiaService.getByNroFamilia(entregaForm.getNroFamilia());
            if (familia == null) {
                result.addError(new ObjectError("nroFamilia", "Número de familia no encontrado."));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }
            // Agrega aquí la validación de que la familia tenga asistidos si es una regla de negocio
            if (familia.getAsistidos() == null || familia.getAsistidos().isEmpty()) {
                result.addError(new ObjectError("nroFamilia", "La familia seleccionada no tiene asistidos registrados."));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }


            Preparacion preparacion = preparacionService.getById(entregaForm.getIdPreparacion());
            if (preparacion == null) {
                result.addError(new ObjectError("idPreparacion", "Preparación (Plato) no encontrado con ID: " + entregaForm.getIdPreparacion()));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }

            Voluntario voluntario = voluntarioService.getVoluntarioById(entregaForm.getIdVoluntario());
            if (voluntario == null) {
                result.addError(new ObjectError("idVoluntario", "Voluntario no encontrado con ID: " + entregaForm.getIdVoluntario()));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }

            // Aquí puedes agregar la validación de raciones disponibles en la preparación
            if (entregaForm.getCantidadRaciones() > preparacion.getStockRacionesRestantes()) {
                result.addError(new ObjectError("cantidadRaciones", "No hay suficientes raciones disponibles de esta preparación. Quedan " + preparacion.getStockRacionesRestantes() + " raciones."));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }
            
            // Aquí puedes agregar la validación de que la cantidad de raciones no exceda el número de asistidos de la familia
            if (entregaForm.getCantidadRaciones() > familia.getAsistidos().size()) {
                result.addError(new ObjectError("cantidadRaciones", "La cantidad de raciones no puede exceder el número de asistidos (" + familia.getAsistidos().size() + ") en la familia."));
                model.addAttribute("familias", familiaService.findAll());
                model.addAttribute("preparaciones", preparacionService.findAll());
                model.addAttribute("voluntarios", voluntarioService.findAll());
                return "entregaAsistenciaAlta";
            }

            EntregaAsistencia nuevaEntrega = new EntregaAsistencia();
            nuevaEntrega.setFecha(LocalDate.now()); // La fecha de la entrega se establece automáticamente
            nuevaEntrega.setFamilia(familia);
            nuevaEntrega.setPreparacion(preparacion);
            nuevaEntrega.setCantidadRaciones(entregaForm.getCantidadRaciones());
            nuevaEntrega.setVoluntario(voluntario);

            entregaAsistenciaService.registrarEntrega(nuevaEntrega);
            return "redirect:/entregaAsistenciaMenu/listado"; // <-- CAMBIO EN REDIRECCIÓN
        } catch (Excepcion e) {
            result.addError(new ObjectError("globalError", e.getMessage()));
            model.addAttribute("familias", familiaService.findAll());
            model.addAttribute("preparaciones", preparacionService.findAll());
            model.addAttribute("voluntarios", voluntarioService.findAll());
            return "entregaAsistenciaAlta";
        }
    }

    // --- HISTORIA 1: LISTAR ENTREGAS (Anteriormente /entregasAsistencia/listado) ---

    @GetMapping("/listado") // Maneja GET /entregasAsistenciaMenu/listado
    public String listarEntregas(
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(value = "nroFamilia", required = false) Integer nroFamilia,
            @RequestParam(value = "nombreFamilia", required = false) String nombreFamilia,
            Model model) {

        if (fecha == null) {
            fecha = LocalDate.now(); // Por defecto, filtra por hoy si no se especifica
        }

        List<EntregaAsistencia> entregas = entregaAsistenciaService.buscarEntregasFiltradas(fecha, nroFamilia, nombreFamilia);
        model.addAttribute("entregas", entregas);
        model.addAttribute("fechaFiltro", fecha);
        model.addAttribute("nroFamiliaFiltro", nroFamilia);
        model.addAttribute("nombreFamiliaFiltro", nombreFamilia);

        return "entregaAsistenciaListado"; // Tu HTML para el listado de entregas
    }

    // --- HISTORIA 3: ELIMINAR ENTREGA (Anteriormente /entregasAsistencia/eliminar/{id}) ---

    @GetMapping("/eliminar/{id}") // Maneja GET /entregasAsistenciaMenu/eliminar/{id}
    public String eliminarEntrega(@PathVariable("id") Long id, Model model) {
        try {
            entregaAsistenciaService.eliminarEntrega(id);
            return "redirect:/entregaAsistenciaMenu/listado"; // <-- CAMBIO EN REDIRECCIÓN
        } catch (Excepcion e) {
            model.addAttribute("error", "No se pudo eliminar la entrega: " + e.getMessage());
            // Si hay un error, redirigir al listado con un mensaje de error
            // Podrías pasar el mensaje de error como un FlashAttribute para que se muestre en la página de listado
            // return "redirect:/entregasAsistenciaMenu/listado?error=" + e.getMessage(); // Ejemplo
            return "redirect:/entregaAsistenciaMenu/listado"; // Por simplicidad, volvemos al listado
        }
    }

    // TODO: Si necesitas funcionalidad de 'editar' una Entrega, añadiría los métodos aquí, similar a como lo hace FamiliaRegistrarEditarController
    // @GetMapping("/editar/{id}")
    // public String preparaFormEdicionEntrega(Model modelo, @PathVariable("id") Long id) { ... }
    // @PostMapping("/editar")
    // public String submitEdicionEntrega(@ModelAttribute("entregaForm") @Valid EntregaAsistenciaForm formBean, BindingResult result, ModelMap modelo) { ... }
}