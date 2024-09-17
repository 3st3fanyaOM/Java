package modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import servicios.TrasteroEstadoException;

public class Trastero {

	private String codigo;
	private Integer metros;
	private Boolean alquilado;
	private BigDecimal precio;
	private String dni;
	private LocalDate inicio;
	private LocalDate fin;
	private Integer dias;
	private List<Bulto> bultos;

	public Trastero(String codigo, Integer metros) {
		super();
		this.codigo = codigo;
		this.metros = metros;
		this.bultos = new ArrayList<Bulto>();
		this.alquilado = false;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Integer getMetros() {
		return metros;
	}

	public void setMetros(Integer metros) {
		this.metros = metros;
	}

	public Boolean getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(Boolean alquilado) {
		this.alquilado = alquilado;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public List<Bulto> getBultos() {
		return bultos;
	}

	public void setBultos(List<Bulto> bultos) {
		this.bultos = bultos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trastero other = (Trastero) obj;
		return Objects.equals(codigo, other.codigo);
	}

	public void alquilar(String dni, Integer dias, BigDecimal precio) throws TrasteroEstadoException {
		if (alquilado) {
			throw new TrasteroEstadoException("Trastero ya alquilado");
		}
		alquilado = true;
		inicio = LocalDate.now();
		inicio = inicio.plusDays(dias);

	}

	public BigDecimal getImporteAlquiler() throws TrasteroEstadoException {
		if (!alquilado) {
			throw new TrasteroEstadoException("Trastero no alquilado");
		}

		BigDecimal importe = BigDecimal.ZERO;
		return importe.add(precio.multiply(new BigDecimal(metros).multiply(new BigDecimal(dias))).setScale(2,
				RoundingMode.HALF_UP));
	}

}
