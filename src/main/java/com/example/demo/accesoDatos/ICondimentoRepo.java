package com.example.demo.accesoDatos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entidades.Condimento;

@Repository
public interface ICondimentoRepo extends JpaRepository<Condimento, Long> {
	

}
