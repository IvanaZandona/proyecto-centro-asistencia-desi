package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "src.main.java")

/*@SpringBootApplication(scanBasePackages = {
    "com.example.demo",
    "presentacion",
    "accesoDatos",
    "entidades",
    "excepciones",
    "servicios"
})*/
public class ArtecheOjedaNavarroZandonaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtecheOjedaNavarroZandonaApplication.class, args);
	}

}
