package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Broker {
	private static Broker yo;
	//private String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015?noAccessToProcedureBodies=true";
	private String url="jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true";
	
	private Broker() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	public static Broker get() throws Exception{
		if(yo==null){
			yo=new Broker();
		}
		return yo;
	}

	public Connection getConnectionSeleccion() throws SQLException {
		return DriverManager.getConnection(url, "selectorTSW2015", "selectorTSW2015");
	}
	
	public Connection getConnectionInsercion() throws SQLException {
		return DriverManager.getConnection(url, "inserterTyS2015", "inserterTyS2015");
	}

	public Connection getConnection(String userName, String pwd) throws SQLException {
		return DriverManager.getConnection(url, userName, pwd);
	}
	
	public Connection getDB(String email, String password) throws SQLException{
		Connection db=getConnectionSeleccion();
		try{
			String SQL ="Select id from usuarios where email=?";
			PreparedStatement p=db.prepareStatement(SQL);
			p.setString(1, email);
			ResultSet r=p.executeQuery();
			Connection result = null;
			if(r.next()){
				int id=r.getInt(1);
				String idUsuario="tysweb2015" + id;
				result=DriverManager.getConnection(url, idUsuario, password);
				r.close();
			}else{
				throw new SQLException("Login o pasword inválidos");
			}
			return result;
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}
	}
}
