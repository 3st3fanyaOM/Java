package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Coche {

	private String matricula;
	private String marca;
	private BigDecimal precio;
	private LocalDate fechaHoraCompra;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public LocalDate getFechaHoraCompra() {
		return fechaHoraCompra;
	}

	public void setFechaHoraCompra(LocalDate fechaHoraCompra) {
		this.fechaHoraCompra = fechaHoraCompra;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", marca=" + marca + ", precio=" + precio + ", fechaHoraCompra="
				+ fechaHoraCompra + "]";
	}

}
