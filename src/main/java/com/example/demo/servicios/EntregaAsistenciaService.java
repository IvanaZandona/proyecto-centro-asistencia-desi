package com.example.demo.servicios;

import com.example.demo.entidades.EntregaAsistencia;
import com.example.demo.excepciones.Excepcion;
import java.time.LocalDate;
import java.util.List;

public interface EntregaAsistenciaService {
	
    EntregaAsistencia registrarEntrega(EntregaAsistencia entrega) throws Excepcion;
    
    void eliminarEntrega(Long id) throws Excepcion;
    
    List<EntregaAsistencia> buscarEntregasPorFecha(LocalDate fecha);
    
    List<EntregaAsistencia> buscarEntregasFiltradas(LocalDate fecha, Integer nroFamilia, String nombreFamilia);
    
    EntregaAsistencia getEntregaAsistenciaById(Long id) throws Excepcion;
}