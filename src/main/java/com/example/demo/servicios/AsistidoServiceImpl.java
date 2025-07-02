package com.example.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.accesoDatos.IAsistidoRepo;
import com.example.demo.accesoDatos.IPersonaRepo;
import com.example.demo.entidades.Asistido;

@Service
public class AsistidoServiceImpl implements AsistidoService{

	@Autowired
    private IAsistidoRepo asistidoRepo;

    @Autowired
    private IPersonaRepo personaRepo;

    @Override
    public void guardar(Asistido asistido) {
        asistidoRepo.save(asistido);
    }

    @Override
    public boolean existePorDni(Integer dni) {
        return personaRepo.existsByDni(dni);
    }
    
}
