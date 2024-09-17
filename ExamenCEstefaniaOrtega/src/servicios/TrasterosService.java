package servicios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Trastero;

public class TrasterosService {

	private OpenConnection openConn;

	public TrasterosService() {
		openConn = new OpenConnection();
	}

	public void actualizarPrecioLibroNuevo(Trastero t) throws TrasteroserviceException, TrasteroEstadoException {

		String sql = "UPDATE TRASTEROS SET PRECIO_METRO_DIA = ?, DNI_ALQUILER = ?, FECHA_INICIO_ALQUILER = ? FECHA_FIN_ALQUILER = ? where COD_TRASTERO = ?";

		try (Connection conn = openConn.getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setBigDecimal(1, t.getPrecio());
			stmt.setString(2, t.getDni());
			stmt.setDate(3, Date.valueOf(t.getInicio()));
			stmt.setDate(4, Date.valueOf(t.getFin()));
			stmt.setString(5, t.getCodigo());
			// t.setAlquilado("S"); ????
			Boolean cambiado = stmt.execute();
			if (!cambiado) {
				throw new TrasteroEstadoException("No se ha encontrado trastero con el codigo indicado");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new TrasteroserviceException("Error actualizando en BBDD");
		}

	}

	public void crearTrastero(Trastero t) throws TrasteroserviceException {

		String sql = "INSERT INTO TRASTEROS VALUES (?, ?)";
		try (Connection conn = openConn.getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, t.getCodigo());
			stmt.setInt(2, t.getMetros());
			// t.setAlquilado("N"); ?????
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new TrasteroserviceException("Error insertando en BBDD");
		}

	}
}
