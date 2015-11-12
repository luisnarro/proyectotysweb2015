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
			/*if(r > 0){
				recordar.idRecuperar = r;
			}else{
				throw new SQLException("Error al insertar el token.");
			}*/
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}
	}
}
