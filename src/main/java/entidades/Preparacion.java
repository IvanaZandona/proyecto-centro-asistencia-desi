package entidades;
import java.time.LocalDate;

public class Preparacion {

	//private String id;
	private Integer totalRacionespreparadas, stockRacionesRestantes;
	private LocalDate fechaCoccion;
	
	public Preparacion(Integer totalRacionespreparadas, Integer stockRacionesRestantes, LocalDate fechaCoccion) {
        this.totalRacionespreparadas = totalRacionespreparadas;
        this.stockRacionesRestantes = stockRacionesRestantes;
        this.fechaCoccion = fechaCoccion;
    }

    public Integer getTotalRacionespreparadas() {
        return totalRacionespreparadas;
    }

    public Integer getStockRacionesRestantes() {
        return stockRacionesRestantes;
    }

    public LocalDate getFechaCoccion() {
        return fechaCoccion;
    }

    public void setTotalRacionespreparadas(Integer totalRacionespreparadas) {
        this.totalRacionespreparadas = totalRacionespreparadas;
    }

    public void setStockRacionesRestantes(Integer stockRacionesRestantes) {
        this.stockRacionesRestantes = stockRacionesRestantes;
    }

    public void setFechaCoccion(LocalDate fechaCoccion) {
        this.fechaCoccion = fechaCoccion;
    }
}
