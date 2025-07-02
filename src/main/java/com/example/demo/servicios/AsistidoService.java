package com.example.demo.servicios;
import com.example.demo.entidades.Asistido;

public interface AsistidoService {

	void guardar(Asistido asistido);
    boolean existePorDni(Integer dni);
    
}
