package com.example.demo.servicios;

import java.util.List;

import com.example.demo.entidades.Familia;
import com.example.demo.excepciones.Excepcion;
import java.time.LocalDate;
import java.util.Map;


public interface FamiliaService {

	List<Familia> findAll();

    Familia getByNroFamilia(Integer nroFamilia);

    void save(Familia familia) throws Excepcion;

    void deleteByNroFamilia(Integer nroFamilia);

    List<Familia> buscarPorNombre(String nombre);

    List<Familia> buscarPorFiltros(Integer nroFamilia, String nombre);
    
    Map<Integer, LocalDate> obtenerUltimaAsistenciaPorFamilia();
    
}
