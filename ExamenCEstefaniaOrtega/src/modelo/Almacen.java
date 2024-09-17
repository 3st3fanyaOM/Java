package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import servicios.TrasteroEstadoException;

public class Almacen {

	private String direccion;
	private List<Trastero> trasteros;

	public Almacen(String direccion) {
		this.trasteros = new ArrayList<Trastero>();
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Trastero> getTrasteros() {
		return trasteros;
	}

	public void setTrasteros(List<Trastero> trasteros) {
		this.trasteros = trasteros;
	}

	public Integer actualizarTrasterosFinAlquiler() {
		Integer alquilados = 0;
		for (Trastero t : trasteros) {
			if (t.getAlquilado() && t.getFin().isBefore(LocalDate.now())) {
				t.setAlquilado(false);
				t.getBultos().clear();
				alquilados++;
			}
		}
		return alquilados;
	}

	public Map<String, Integer> getMapaClientes() {
		Map<String, Integer> mapaAlquilados = new HashMap<String, Integer>();

		for (Trastero t : trasteros) {
			if (t.getAlquilado()) {
				mapaAlquilados.put(t.getDni(), t.getMetros());
				// no sabia como poner el numero de trasteros, no hay ningun atributo con esa
				// cantidad.
			}
		}
		return mapaAlquilados;

	}

	public Trastero getTrasteroLibre(Integer minimo) throws TrasteroEstadoException {
		Trastero tlibre = null;
		for (Trastero t : trasteros) {
			if (!t.getAlquilado()) {
				if (t.getMetros() >= minimo) {
					tlibre = new Trastero(t.getCodigo(), t.getMetros());
				}
			}
			throw new TrasteroEstadoException("Trastero no alquilado");
		}
		return tlibre;
	}

	public Trastero getTrasteroMayorBulto() throws TrasteroEstadoException {
		Trastero mayorBulto = null;

		for (Trastero t : trasteros) {
			if (t.getBultos().isEmpty()) {
				throw new TrasteroEstadoException("No hay ningun trastero con bultos");
			}
			mayorBulto = t;
		}
		if (mayorBulto == null) {
			throw new TrasteroEstadoException("No hay trasteros");
		}
		return mayorBulto;
	}

}
