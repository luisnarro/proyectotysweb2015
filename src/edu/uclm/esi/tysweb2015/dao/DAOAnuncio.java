package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;

public class DAOAnuncio {
	
	public static void insert(Anuncio anuncio) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionInsercion();
		try{
			String sql="{call insertarAnuncio (?, ?, ?, ?, ?)}";
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnunciante());
			cs.setString(2, anuncio.getDescripcion());
			cs.setInt(3, anuncio.getIdCategoria());
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.registerOutParameter(5, java.sql.Types.TIMESTAMP);
			cs.executeUpdate();
			int exito=cs.getInt(4);
			if (exito<=0)
				throw new SQLException("Error al insertar anuncio. Código: "+exito);
			anuncio.setIdAnuncio(exito);
			anuncio.setFechaAlta(cs.getInt(5));
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static int addFoto(Anuncio anuncio, String identificador, String uploadContentType) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionInsercion();
		try{
			String sql="{call addFoto (?, ?, ?, ?)}";
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, anuncio.getIdAnuncio());
			cs.setString(2, identificador);
			cs.setString(3, uploadContentType);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.executeUpdate();
			int exito=cs.getInt(4);
			if (exito<=0)
				throw new SQLException("Error al insertar la foto. Código: "+exito);
			return exito;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static void recuperar(Anuncio anuncio) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT fechaDeAlta, descripcion, idCategoria FROM Anuncios where idAnunciante=? AND id=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setInt(1, anuncio.getIdAnunciante());
			p.setInt(2, anuncio.getIdAnuncio());
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				int fecha = rs.getInt(1);
				String descripcion = rs.getString(2);
				int idCategoria = rs.getInt(3);
				anuncio.setFechaAlta(fecha);
				anuncio.setDescripcion(descripcion);
				anuncio.setIdCategoria(idCategoria);
			}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static void recuperarFotos(Anuncio anuncio) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT identificador, contentType FROM Fotos where idAnuncio=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setInt(1, anuncio.getIdAnuncio());
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				String identificador = rs.getString(1);
				String contentType = rs.getString(2);
				
				String formato = contentType.split("/")[1];
				
				anuncio.getFotos().put("foto", identificador + "." + formato);
			}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

}
