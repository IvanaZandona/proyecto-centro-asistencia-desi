package entidades;

import java.time.LocalDate;
import jakarta.persistence.Entity;


@Entity
public class EntregaAsistencia {

	private LocalDate fecha;
	private Integer cantidadRaciones;
	private Long id;
	
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
        this.fechaRegistro = fechaRegistro;
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
}
