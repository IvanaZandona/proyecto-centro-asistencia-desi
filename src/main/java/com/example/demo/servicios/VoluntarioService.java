package com.example.demo.servicios;

import com.example.demo.entidades.Voluntario;
import com.example.demo.excepciones.Excepcion;
import java.util.List;

public interface VoluntarioService {
    Voluntario getVoluntarioById(Long id) throws Excepcion;
    List<Voluntario> findAll();
}