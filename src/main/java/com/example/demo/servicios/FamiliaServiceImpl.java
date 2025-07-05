package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.accesoDatos.IAsistidoRepo;
import com.example.demo.accesoDatos.IFamiliaRepo;
import com.example.demo.entidades.Familia;
import com.example.demo.entidades.Asistido;
import com.example.demo.excepciones.Excepcion;

//para forzar conexion
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class FamiliaServiceImpl implements FamiliaService {

	@Autowired
    private IFamiliaRepo familiaRepo;
	
	@Autowired
    private IAsistidoRepo asistidoRepo;
	
	//para forzar el insert
	@Autowired
	private EntityManager em;
	
	@Override
    public List<Familia> findAll() {
        return familiaRepo.findAll();
    }
	
	@Override
    public Familia getByNroFamilia(Integer nroFamilia) {
        return familiaRepo.findById(nroFamilia).orElse(null);
    }

	@Override
	@Transactional
	public void save(Familia familia) throws Excepcion {
		if (familia.getNombre() == null || familia.getNombre().isBlank()) {
			throw new Excepcion("validacion", "El nombre de la familia es obligatorio.");
		}
		
		System.out.println("Guardando familia: " + familia.getNombre() + " con " + familia.getAsistidos().size() + " integrantes.");
		
		try {
			familiaRepo.save(familia);
			em.flush(); // fuerza los INSERT inmediatamente
		} catch (DataIntegrityViolationException ex) {
	        Throwable cause = ex.getMostSpecificCause();
	        if (cause != null && cause.getMessage() != null && cause.getMessage().toLowerCase().contains("duplicate")) {
	            String mensaje = cause.getMessage();
	            String dniDuplicado = extraerValorDuplicado(mensaje); //extra para validacion
	            throw new Excepcion("dni_duplicado", "El DNI " + dniDuplicado + " ya está registrado. Limpie los campos e intente nuevamente.", "dni");
	        }
	        throw new Excepcion("general", "Error de integridad de datos: " + ex.getMessage());
	    } catch (Exception e) {
	        throw new Excepcion("general", "Ocurrió un error al guardar la familia.");
	    }
	}
	
	@Override
    public void deleteByNroFamilia(Integer nroFamilia) {
        familiaRepo.deleteById(nroFamilia);
    }

    @Override
    public List<Familia> buscarPorNombre(String nombre) {
        return familiaRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Familia> buscarPorFiltros(Integer nroFamilia, String nombre) {
        if (nroFamilia != null) {
            return familiaRepo.buscarConAsistidosPorNro(nroFamilia)
                .filter(f -> !f.isEliminado())
                .map(List::of)
                .orElse(List.of());
        }
        if (nombre != null && !nombre.trim().isEmpty()) {
            return familiaRepo.buscarConAsistidosPorNombre(nombre)
                .stream()
                .filter(f -> !f.isEliminado())
                .toList();           
        }
        return familiaRepo.findAllConAsistidos()
            .stream()
            .filter(f -> !f.isEliminado())
            .toList();
    }

    @Override
    public Map<Integer, LocalDate> obtenerUltimaAsistenciaPorFamilia() {
        List<Object[]> resultados = familiaRepo.obtenerFechaUltimaAsistenciaPorFamilia();
        Map<Integer, LocalDate> mapa = new HashMap<>();

        for (Object[] fila : resultados) {
            Integer nroFamilia = (Integer) fila[0];
            LocalDate fecha = (LocalDate) fila[1]; // puede ser null
            mapa.put(nroFamilia, fecha);
        }

        return mapa;
    }
    
    //anexo validacion de dni
    private String extraerValorDuplicado(String mensaje) {
        try {
            // Ejemplo de mensaje: "Duplicate entry '123' for key 'persona.dni_UNIQUE'"
            int start = mensaje.indexOf("Duplicate entry '") + "Duplicate entry '".length();
            int end = mensaje.indexOf("'", start);
            return mensaje.substring(start, end);
        } catch (Exception e) {
            return "?"; // si falla, devolver incógnita
        }
    }


}
