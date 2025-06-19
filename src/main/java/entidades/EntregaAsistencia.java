package entidades;

public class EntregaAsistencia {

	private LocalDate fecha;
	private Integer cantidadRaciones;
	//private String id;
	
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
}
