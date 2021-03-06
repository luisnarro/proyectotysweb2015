package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Recordar;

public class DAORecordar {

	public static void comprobarUsuario(Recordar recordar) throws SQLException, Exception {
		Conexion db=Broker.get().getConnectionSeleccion();
		try{
			String SQL ="Select id from usuarios where email=?";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, recordar.email);
			ResultSet r=p.executeQuery();
			if(r.next()){
				int id=r.getInt(1);
				String idUsuario="tysweb2015" + id;
				recordar.idUsuario = idUsuario;
				r.close();
			}else{
				throw new SQLException("El email no es v�lido.");
			}
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}
	}

	public static void guardarToken(Recordar recordar) throws SQLException, Exception {
		Conexion db=Broker.get().getConnectionInsercion();
		try{
			String SQL ="insert into recuperar(idUsuario,token) values(?,?)";
			PreparedStatement p=db.prepareStatement(SQL);
			String idUsuario = recordar.idUsuario;
			int id = Integer.parseInt(idUsuario.substring(10));
			p.setInt(1, id);
			p.setString(2, recordar.token);
			p.executeUpdate();
		}
		catch (SQLException e){
			if (e.getSQLState().equals("23000"))
				throw new Exception("Ya hemos enviado un email. Comprueba tu correo.");
			else
				throw e;
		}
		finally{
			db.close();
		}
	}

	public static void comprobarToken(Recordar recordar) throws SQLException, Exception {
		Conexion db=Broker.get().getConnectionSeleccion();
		String idUsuario;
		try{
			String SQL ="Select id,idUsuario from recuperar where token=?";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, recordar.getToken());
			ResultSet r=p.executeQuery();
			if(r.next()){
				int id=r.getInt(2);
				idUsuario="tysweb2015" + id;
				recordar.setIdUsuario(idUsuario);
				r.close();
			}else{
				recordar.eliminarToken();
				throw new SQLException("Token no v�lido.");
			}
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}	
	}
	
	public static void comprobarFecha(Recordar recordar) throws SQLException, Exception {
		Conexion db=Broker.get().getConnectionSeleccion();
		String idUsuario;
		try{
			String SQL ="Select id,idUsuario from recuperar where token=? and expira > now()";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, recordar.getToken());
			ResultSet r=p.executeQuery();
			if(r.next()){
				int id=r.getInt(2);
				idUsuario="tysweb2015" + id;
				recordar.setIdUsuario(idUsuario);
				r.close();
			}else{
				throw new SQLException("El token expir�.");
			}
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}	
	}

	public static void eliminarToken(String token) throws SQLException, Exception {
		Conexion db=Broker.get().getConnectionDelete();

		try{
			String SQL ="delete from recuperar where token=?";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, token);
			p.execute();
		}
		catch (SQLException e){
			throw new Exception("Error al eliminar el token.");
		}
		finally{
			db.close();
		}	
	}

	public static void updatePwd(String pwd, String idUsuario) throws SQLException, Exception {
		//Conexion db=Broker.get().getConnectionInsercion();
		Connection db = Broker.get().getConnection("root", "pass");
		try{
			String SQL ="SET PASSWORD FOR ?@localhost = PASSWORD(?)";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(2, pwd);
			p.setString(1, idUsuario);
			p.execute();
		}
		catch (SQLException e){
			throw new Exception("No se ha podido actualizar la contrase�a.");
		}
		finally{
			db.close();
		}	
	}
}
