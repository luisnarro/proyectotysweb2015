package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Deseo;

public class DAODeseo {

	public static boolean comprobarExiste(Deseo deseo) throws SQLException, Exception {
		boolean result = false;
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT l.* FROM listadeseos l, usuarios u where l.idAnuncio=? AND u.email=? AND l.idUsuario=u.id";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setInt(1, deseo.getIdAnuncio());
			p.setString(2, deseo.getUsuario());
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				result = true;
			}
		}
		catch(Exception e){
			throw new Exception("No se ha podido comprobar este anuncio de la lista.");
		}
		finally{
			bd.close();
		}
		return result;
	}

	public static void insertar(Deseo deseo) throws SQLException, Exception {
		int id = getIdUsuario(deseo.getUsuario());
		Conexion bd = Broker.get().getConnectionInsercion();
		try{
			String sql="{call insertarDeseo (?, ?)}";
			CallableStatement cs=bd.prepareCall(sql);
			cs.setInt(1, id);
			cs.setInt(2, deseo.getIdAnuncio());
			cs.executeUpdate();
		}
		catch(Exception e){
			throw new Exception("Error al guardar el anuncio en la lista.");
		}
		finally{
			bd.close();
		}
	}
	
	private static int getIdUsuario(String email) throws SQLException, Exception{
		int result = 0;
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT id FROM usuarios where email=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);;
			}
		}
		catch(Exception e){
			throw new Exception("Usuario no válido.");
		}
		finally{
			bd.close();
		}
		return result;
	}

	public static void eliminar(Deseo deseo) throws SQLException, Exception {
		int id = getIdUsuario(deseo.getUsuario());
		Conexion bd = Broker.get().getConnectionDelete();
		try{
			String sql= "DELETE FROM listadeseos where idUsuario=? AND idAnuncio=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setInt(1, id);
			p.setInt(2, deseo.getIdAnuncio());
			p.execute();
		}
		catch(Exception e){
			throw new Exception("No se ha podido eliminar este anuncio de la lista.");
		}
		finally{
			bd.close();
		}
	}

}
