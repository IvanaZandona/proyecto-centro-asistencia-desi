package com.example.demo.servicios;

import com.example.demo.entidades.Receta; // Asumo que tienes una entidad Receta
import com.example.demo.excepciones.Excepcion; // Si tus métodos lanzan esta excepción

import java.util.List;

public interface RecetaService {
    List<Receta> getAll();             // <-- ¡AÑADIR ESTE MÉTODO!
    Receta getById(Long id) throws Excepcion; // <-- ¡AÑADIR ESTE MÉTODO! (considera si necesitas throws Excepcion)

    // Probablemente también necesites estos para un CRUD completo de Receta:
    void save(Receta receta) throws Excepcion;
    void deleteById(Long id) throws Excepcion;
    // Si tienes un filtro para Recetas, también lo añadirías aquí
    // List<Receta> filter(RecetaBuscarForm form) throws Excepcion;
}