package entidades;

public class Producto extends Ingrediente {

	private float stockDisponible, precioActual;
	
	public Producto(String nombre, Integer calorias, float stockDisponible, float precioActual) {
		super(nombre, calorias);
		this.stockDisponible = stockDisponible;
		this.precioActual = precioActual;
	}

    public float getStockDisponible() {
        return stockDisponible;
    }

    public float getPrecioActual() {
        return precioActual;
    }

    public void setStockDisponible(float stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public void setPrecioActual(float precioActual) {
        this.precioActual = precioActual;
    }
}
