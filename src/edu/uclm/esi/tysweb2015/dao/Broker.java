package edu.uclm.esi.tysweb2015.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Broker {
	private static Broker yo;
	private Pool pool;
	//private String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015?noAccessToProcedureBodies=true";
	private String url;
	
	private Broker(){
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			this.pool=new Pool(30, 30, 10);
			url="jdbc:mysql://localhost:3306/tysweb2015?noAccessToProcedureBodies=true";
		}catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	private static class BrokerHolder{
		private static Broker broker = new Broker();
	}
	
	public static Broker get() throws Exception{
		return BrokerHolder.broker;
	}

	public Conexion getConnectionSeleccion() throws SQLException {
		//return DriverManager.getConnection(url, "selectorTSW2015", "selectorTSW2015");
		return this.pool.getConexionSelection();
	}
	
	public Conexion getConnectionInsercion() throws SQLException {
		//return DriverManager.getConnection(url, "inserterTyS2015", "inserterTyS2015");
		return this.pool.getConexionInsercion();
	}
	
	public Conexion getConnectionDelete() throws SQLException {
		//return DriverManager.getConnection(url, "deleteTyS2015", "deleteTyS2015");
		return this.pool.getConexionDelete();
	}

	public Connection getConnection(String userName, String pwd) throws SQLException {
		return DriverManager.getConnection(url, userName, pwd);
	}
	
	public boolean existe(String email, String password) throws SQLException{
		boolean resultado=false;
		Conexion db=getConnectionSeleccion();
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
				resultado=true;
				result.close();
				r.close();
			}else{
				throw new SQLException("Login o pasword inválidos");
			}
			return resultado;
		}
		catch (SQLException e){
			throw e;
		}
		finally{
			db.close();
		}
	}
}
