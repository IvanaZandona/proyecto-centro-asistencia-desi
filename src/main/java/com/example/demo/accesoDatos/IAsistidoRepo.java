package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Asistido;

public interface IAsistidoRepo extends JpaRepository<Asistido, Long>{

}
