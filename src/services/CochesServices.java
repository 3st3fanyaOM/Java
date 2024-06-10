package services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Coche;

public class CochesServices {

	private OpenConnection openConn;

	public CochesServices() {
		openConn = new OpenConnection();
	}

	private Coche getCocheFromResultSet(ResultSet rs) throws SQLException {
		Coche c = new Coche();

		c.setMatricula(rs.getString("matricula"));
		c.setFechaHoraCompra(rs.getDate("fechaHoraCompra").toLocalDate());
		c.setMarca(rs.getString("marca"));
		c.setPrecio(rs.getBigDecimal("precio"));

		return c;
	}

	public void insertarCoche(Coche coche) throws SQLException {

		ResultSet rs = null;
		String sql = "insert into coches values (?,?,?,?) ";
		try (Connection conn = openConn.getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, coche.getMatricula());
			stmt.setString(2, coche.getMarca());
			stmt.setBigDecimal(3,coche.getPrecio());
			stmt.setDate(4, Date.valueOf(coche.getFechaHoraCompra()));

			System.out.println(sql);
			stmt.execute();// por que es un insert EXECUTE

		}

	}

	public Coche consultarCoche(String matricula) throws SQLException {
		String sql = "select * from coches where matricula = ? ";
		ResultSet rs = null;
		try (Connection conn = openConn.getNewConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, matricula);
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				Coche c = getCocheFromResultSet(rs);
				return c;
			} else {
				return null;
			}

		}
	}
	
	public Integer actualizarCoche(Coche coche) throws SQLException {

		String sql = "update coches set matricula = ?, marca = ?, precio = ?, where fecha_hora_compra = ?";
		try (Connection conn = openConn.getNewConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, coche.getMatricula());
			stmt.setString(2, coche.getMarca());
			stmt.setBigDecimal(3, coche.getPrecio());
			stmt.setDate(4, Date.valueOf(coche.getFechaHoraCompra()));

			System.out.println(sql);
			Integer registros = stmt.executeUpdate();// por que es un insert EXECUTEUPDAte
			return registros;

		}

	}
	
	public Integer borrarCoche(String matricula) throws SQLException {
		String sql = "delete from personas where dni = ?";

		try (Connection conn = openConn.getNewConnection(); 
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, matricula);

			Integer registros = stmt.executeUpdate();// por que es un delete EXECUTEUPDAte
			return registros;
		}

	}

}
