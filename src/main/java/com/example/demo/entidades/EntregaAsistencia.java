package com.example.demo.entidades;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "entrega_asistencia")
public class EntregaAsistencia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identrega_asistencia")
	private Long id;
	
	@Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "cantidad_raciones")
    private Integer cantidadRaciones;
    
    @ManyToOne
    @JoinColumn(name = "familia_nro_familia", nullable = false)
    private Familia familia;

    @ManyToOne
    @JoinColumn(name = "preparacion_idpreparacion", nullable = false)
    private Preparacion preparacion;

    @ManyToOne
    @JoinColumn(name = "voluntario_persona_idpersona", nullable = false)
    private Voluntario voluntario;
	
	public EntregaAsistencia() {
	    // Constructor vacío requerido por JPA
	}
	
	public EntregaAsistencia(LocalDate fecha, Integer cantidadRaciones){
		this.fecha = fecha;
		this.cantidadRaciones = cantidadRaciones;
	}
	
	public LocalDate getFecha() {
        return fecha;
    }
	public Integer getCantidadRaciones() {
		return cantidadRaciones;
	}
	public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
	public void setCantidadRaciones(Integer cantidadRaciones) {
		this.cantidadRaciones = cantidadRaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Preparacion getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(Preparacion preparacion) {
        this.preparacion = preparacion;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
}
