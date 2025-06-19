package entidades;

public class Familia {

	private Integer nroFamilia;
	private String nombre;
	private LocalDate fechaRegistro;
	
	public Familia(Integer nroFamilia, String nombre, LocalDate fechaRegistro) {
		this.nroFamilia = nroFamilia;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
	}
	
}
