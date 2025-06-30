package com.example.demo.accesoDatos;

import com.example.demo.entidades.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVoluntarioRepo extends JpaRepository<Voluntario, Long> { // Asumo Long como ID de Persona/Voluntario
}