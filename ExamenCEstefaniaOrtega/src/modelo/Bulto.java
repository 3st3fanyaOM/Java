package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Bulto {

	private String descripcion;
	private BigDecimal peso;
	private Boolean voluminoso;

	public Bulto(String descripcion, BigDecimal peso) {
		super();
		this.descripcion = descripcion;
		this.peso = peso;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public Boolean getVoluminoso() {
		return voluminoso;
	}

	public void setVoluminoso(Boolean voluminoso) {
		this.voluminoso = voluminoso;
	}

	public BigDecimal getSobreCosteDiario() {

		if (peso.compareTo(new BigDecimal(50)) > 0) {
			return BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		}
		return BigDecimal.ZERO;
	}

}
