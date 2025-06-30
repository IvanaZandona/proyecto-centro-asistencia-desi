package com.example.demo.presentacion;

import com.example.demo.entidades.EntregaAsistencia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EntregaAsistenciaForm {

    private Long id;

    @NotNull(message = "El número de familia es obligatorio")
    @Min(value = 1, message = "El número de familia debe ser positivo")
    private Integer nroFamilia;

    @NotNull(message = "El ID de la preparación es obligatorio")
    @Min(value = 1, message = "El ID de la preparación debe ser positivo")
    private Long idPreparacion;

    @NotNull(message = "La cantidad de raciones es obligatoria")
    @Min(value = 1, message = "La cantidad de raciones debe ser al menos 1")
    private Integer cantidadRaciones;

    @NotNull(message = "El ID del voluntario es obligatorio")
    @Min(value = 1, message = "El ID del voluntario debe ser positivo")
    private Long idVoluntario;

    public EntregaAsistenciaForm() {}

    public EntregaAsistenciaForm(EntregaAsistencia entrega) {
        this.id = entrega.getId();
        this.nroFamilia = entrega.getFamilia().getNroFamilia();
        this.idPreparacion = entrega.getPreparacion().getId();
        this.cantidadRaciones = entrega.getCantidadRaciones();
        this.idVoluntario = entrega.getVoluntario().getId();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getNroFamilia() { return nroFamilia; }
    public void setNroFamilia(Integer nroFamilia) { this.nroFamilia = nroFamilia; }
    public Long getIdPreparacion() { return idPreparacion; }
    public void setIdPreparacion(Long idPreparacion) { this.idPreparacion = idPreparacion; }
    public Integer getCantidadRaciones() { return cantidadRaciones; }
    public void setCantidadRaciones(Integer cantidadRaciones) { this.cantidadRaciones = cantidadRaciones; }
    public Long getIdVoluntario() { return idVoluntario; }
    public void setIdVoluntario(Long idVoluntario) { this.idVoluntario = idVoluntario; }
}