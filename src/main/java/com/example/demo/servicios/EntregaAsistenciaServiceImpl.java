package com.example.demo.servicios;

import com.example.demo.accesoDatos.IEntregaRepo;
import com.example.demo.entidades.EntregaAsistencia;
import com.example.demo.entidades.Familia;
import com.example.demo.entidades.Preparacion;
import com.example.demo.entidades.Voluntario;
import com.example.demo.excepciones.Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntregaAsistenciaServiceImpl implements EntregaAsistenciaService {

    @Autowired
    private IEntregaRepo entregaAsistenciaRepository;

    @Autowired
    private FamiliaService familiaService;

    @Autowired
    private PreparacionService preparacionService;

    @Autowired
    private VoluntarioService voluntarioService;

    @Override
    @Transactional
    public EntregaAsistencia registrarEntrega(EntregaAsistencia nuevaEntrega) throws Excepcion {
        // 1. Validar "No podrá haber dos entregas en el mismo día, para la misma familia."
        Optional<EntregaAsistencia> existingEntrega = entregaAsistenciaRepository.findByFechaAndFamilia(
            nuevaEntrega.getFecha(), nuevaEntrega.getFamilia());

        if (existingEntrega.isPresent()) {
            throw new Excepcion("Ya existe una entrega registrada para esta familia (" +
                                nuevaEntrega.getFamilia().getNombre() + ") en la misma fecha (" +
                                nuevaEntrega.getFecha() + ").");
        }

        // 2. Validar "No se puede entregar más raciones que integrantes registrados para la familia."
        Familia familia = nuevaEntrega.getFamilia();
        if (familia == null) {
            throw new Excepcion("La familia especificada para la entrega no es válida.");
        }
        // *** ¡CAMBIO AQUÍ! Usamos getAsistidos().size() ***
        if (nuevaEntrega.getCantidadRaciones() > familia.getAsistidos().size()) {
            throw new Excepcion("No se puede entregar más raciones (" + nuevaEntrega.getCantidadRaciones() +
                                ") que el número de integrantes (" + familia.getAsistidos().size() +
                                ") registrados para la familia: " + familia.getNombre() + ".");
        }

        // 3. Reducir stock: "Una vez dada de alta la receta se debe proceder a dar de baja del stock las raciones entregadas."
        Preparacion preparacion = nuevaEntrega.getPreparacion();
        if (preparacion == null) {
            throw new Excepcion("La preparación (plato) especificada no es válida.");
        }

        if (preparacion.getStockRacionesRestantes() == null ||
            preparacion.getStockRacionesRestantes() < nuevaEntrega.getCantidadRaciones()) {
            throw new Excepcion("No hay suficiente stock de '" + preparacion.getReceta().getNombre() + "' (" +
                                preparacion.getStockRacionesRestantes() + " raciones disponibles) para la entrega de " +
                                nuevaEntrega.getCantidadRaciones() + " raciones.");
        }

        preparacion.setStockRacionesRestantes(preparacion.getStockRacionesRestantes() - nuevaEntrega.getCantidadRaciones());
        preparacionService.save(preparacion);

        // 4. "El sistema guardará automáticamente la fecha de entrega (fecha actual)."
        nuevaEntrega.setFecha(LocalDate.now());

        return entregaAsistenciaRepository.save(nuevaEntrega);
    }

    @Override
    @Transactional
    public void eliminarEntrega(Long id) throws Excepcion {
        EntregaAsistencia entrega = entregaAsistenciaRepository.findById(id)
                                .orElseThrow(() -> new Excepcion("Entrega de asistencia no encontrada con ID: " + id));

        // Devolver el stock: "Una vez eliminada, el stock correspondiente vuelve a estar disponible."
        Preparacion preparacion = entrega.getPreparacion();
        if (preparacion != null) {
            preparacion.setStockRacionesRestantes(preparacion.getStockRacionesRestantes() + entrega.getCantidadRaciones());
            preparacionService.save(preparacion);
        } else {
            System.err.println("Advertencia: No se pudo devolver el stock para la entrega ID " + id + ". Preparación asociada no encontrada.");
        }

        entregaAsistenciaRepository.delete(entrega);
    }

    @Override
    public List<EntregaAsistencia> buscarEntregasPorFecha(LocalDate fecha) {
        return entregaAsistenciaRepository.findByFecha(fecha);
    }

    @Override
    public List<EntregaAsistencia> buscarEntregasFiltradas(LocalDate fecha, Integer nroFamilia, String nombreFamilia) {
        List<EntregaAsistencia> resultados = entregaAsistenciaRepository.findByFecha(fecha);

        if (nroFamilia != null) {
            resultados = resultados.stream()
                                   .filter(e -> e.getFamilia().getNroFamilia().equals(nroFamilia))
                                   .collect(Collectors.toList());
        }

        if (nombreFamilia != null && !nombreFamilia.trim().isEmpty()) {
            String nombreFamiliaLower = nombreFamilia.toLowerCase();
            resultados = resultados.stream()
                                   .filter(e -> e.getFamilia().getNombre().toLowerCase().contains(nombreFamiliaLower))
                                   .collect(Collectors.toList());
        }
        return resultados;
    }

    @Override
    public EntregaAsistencia getEntregaAsistenciaById(Long id) throws Excepcion {
        return entregaAsistenciaRepository.findById(id)
                                .orElseThrow(() -> new Excepcion("Entrega de asistencia no encontrada con ID: " + id));
    }
}