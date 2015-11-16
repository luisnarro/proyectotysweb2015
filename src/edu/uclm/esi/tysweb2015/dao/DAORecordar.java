package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Recordar;

public class DAORecordar {

	public static void comprobarUsuario(Recordar recordar) throws SQLException, Exception {
		Connection db=Broker.get().getConnectionSeleccion();
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
				throw new SQLException("El email no es válido.");
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
		Connection db=Broker.get().getConnectionInsercion();
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
		Connection db=Broker.get().getConnectionSeleccion();
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
				throw new SQLException("Token no válido o expiró.");
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
		Connection db=Broker.get().getConnectionDelete();

		try{
			String SQL ="delete from recuperar where token=?";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, token);
			p.execute();
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}	
	}

	public static void updatePwd(String pwd, String idUsuario) throws SQLException, Exception {
		Connection db=Broker.get().getConnectionInsercion();
		try{
			String SQL ="UPDATE mysql.user SET Password=PASSWORD(?) WHERE User=? AND Host='localhost'";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, pwd);
			p.setString(2, idUsuario);
			p.execute();
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}	
	}
}
