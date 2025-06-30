package com.example.demo.servicios;

import com.example.demo.accesoDatos.IRecetaRepo; // Asumo que tienes un IRecetaRepo
import com.example.demo.entidades.Receta;
import com.example.demo.excepciones.Excepcion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private IRecetaRepo recetaRepo;

    @Override
    public List<Receta> getAll() {
        return recetaRepo.findAll();
    }

    @Override
    public Receta getById(Long id) throws Excepcion {
        return recetaRepo.findById(id)
                         .orElseThrow(() -> new Excepcion("Receta no encontrada con ID: " + id));
    }

    // Métodos adicionales que podrías necesitar implementar
    @Override
    public void save(Receta receta) throws Excepcion {
        // Agrega validaciones si es necesario
        if (receta.getNombre() == null || receta.getNombre().isBlank()) {
            throw new Excepcion("El nombre de la receta no puede estar vacío.");
        }
        recetaRepo.save(receta);
    }

    @Override
    public void deleteById(Long id) throws Excepcion {
        if (!recetaRepo.existsById(id)) {
            throw new Excepcion("Receta no encontrada para eliminar con ID: " + id);
        }
        recetaRepo.deleteById(id);
    }
}