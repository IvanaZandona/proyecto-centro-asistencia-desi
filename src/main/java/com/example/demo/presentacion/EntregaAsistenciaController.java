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
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/entregasAsistencia")
public class EntregaAsistenciaController {

    @Autowired
    private EntregaAsistenciaService entregaAsistenciaService;

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private PreparacionService preparacionService;

    @Autowired
    private VoluntarioService voluntarioService;

    // --- HISTORIA 2: REGISTRAR ENTREGA ---

    @GetMapping("/alta")
    public String mostrarFormularioAltaEntrega(Model model) {
        model.addAttribute("entregaForm", new EntregaAsistenciaForm());
        model.addAttribute("familias", familiaService.findAll());
        model.addAttribute("preparaciones", preparacionService.findAll());
        model.addAttribute("voluntarios", voluntarioService.findAll());
        return "entregaAsistenciaAlta";
    }

    @PostMapping("/alta")
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

            Preparacion preparacion = preparacionService.getPreparacionById(entregaForm.getIdPreparacion());
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

            EntregaAsistencia nuevaEntrega = new EntregaAsistencia();
            nuevaEntrega.setFamilia(familia);
            nuevaEntrega.setPreparacion(preparacion);
            nuevaEntrega.setCantidadRaciones(entregaForm.getCantidadRaciones());
            nuevaEntrega.setVoluntario(voluntario);

            entregaAsistenciaService.registrarEntrega(nuevaEntrega);
            return "redirect:/entregasAsistencia/listado";
        } catch (Excepcion e) {
            result.addError(new ObjectError("globalError", e.getMessage()));
            model.addAttribute("familias", familiaService.findAll());
            model.addAttribute("preparaciones", preparacionService.findAll());
            model.addAttribute("voluntarios", voluntarioService.findAll());
            return "entregaAsistenciaAlta";
        }
    }

    // --- HISTORIA 1: LISTAR ENTREGAS ---

    @GetMapping("/listado")
    public String listarEntregas(
            @RequestParam(value = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(value = "nroFamilia", required = false) Integer nroFamilia,
            @RequestParam(value = "nombreFamilia", required = false) String nombreFamilia,
            Model model) {

        if (fecha == null) {
            fecha = LocalDate.now();
        }

        List<EntregaAsistencia> entregas = entregaAsistenciaService.buscarEntregasFiltradas(fecha, nroFamilia, nombreFamilia);
        model.addAttribute("entregas", entregas);
        model.addAttribute("fechaFiltro", fecha);
        model.addAttribute("nroFamiliaFiltro", nroFamilia);
        model.addAttribute("nombreFamiliaFiltro", nombreFamilia);

        return "entregaAsistenciaListado";
    }

    // --- HISTORIA 3: ELIMINAR ENTREGA ---

    @GetMapping("/eliminar/{id}")
    public String eliminarEntrega(@PathVariable("id") Long id, Model model) {
        try {
            entregaAsistenciaService.eliminarEntrega(id);
            return "redirect:/entregasAsistencia/listado";
        } catch (Excepcion e) {
            model.addAttribute("error", "No se pudo eliminar la entrega: " + e.getMessage());
            return "redirect:/entregasAsistencia/listado";
        }
    }
}