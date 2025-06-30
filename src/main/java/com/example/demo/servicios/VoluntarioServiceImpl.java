package com.example.demo.servicios;

import com.example.demo.accesoDatos.IVoluntarioRepo;
import com.example.demo.entidades.Voluntario;
import com.example.demo.excepciones.Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Importación necesaria

@Service
public class VoluntarioServiceImpl implements VoluntarioService {

    @Autowired
    private IVoluntarioRepo voluntarioRepo;

    @Override
    public Voluntario getVoluntarioById(Long id) throws Excepcion {
        return voluntarioRepo.findById(id)
                             .orElseThrow(() -> new Excepcion("Voluntario no encontrado con ID: " + id));
    }

    @Override
    public List<Voluntario> findAll() {
        return voluntarioRepo.findAll();
    }
}