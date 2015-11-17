package edu.uclm.esi.tysweb2015.dao.test;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.junit.Test;

import com.mysql.jdbc.Connection;

import edu.uclm.esi.tysweb2015.dao.Broker;
import edu.uclm.esi.tysweb2015.dao.Conexion;

public class BrokerTest {

	//@Test
//	public void testAperturaDeConexiones() throws Exception {
//		Broker db=Broker.get();
//		
//		int i=1;
//		boolean error=false;
//		do{
//			try{
//				db.getDB("paco@uclm.es", "pass");
//			}catch (SQLException e) {
//				error=true;
//			}
//			System.out.println(i++);
//		}while (!error);
//		fail("Error a las " + i);
//	}
	
	//@Test
	public void tiemposDeInsercion(){
		String sql = "insert into anuncios (descripcion, idCategoria, idAnunciante) values (?,?,?)";
		
		//Conexión abierta
		try{
			long timeIni = System.currentTimeMillis();
			Conexion db=Broker.get().getConnectionInsercion();
			PreparedStatement p=db.prepareStatement(sql);
			for(int i=0; i<300; i++){
				p.setString(1, "Anuncio de un coche de prueba");
				p.setInt(2, 4);
				p.setInt(3, 32);
				p.executeUpdate();
			}
			db.close();
			long timeFinal = System.currentTimeMillis();
			System.out.println("Tiempo sin cerrar: " + (timeFinal-timeIni)/1000);
		}catch(Exception e){
			fail("Exceptión inesperada: " + e);
		}
		
		//Conexión abriendo-cerrando
		try{
			long timeIni = System.currentTimeMillis();
			for(int i=0; i<300; i++){
				Conexion db=Broker.get().getConnectionInsercion();
				PreparedStatement p=db.prepareStatement(sql);
				p.setString(1, "Anuncio de un coche de prueba");
				p.setInt(2, 4);
				p.setInt(3, 32);
				p.executeUpdate();
				db.close();
			}
			long timeFinal = System.currentTimeMillis();
			System.out.println("Tiempo abriendo-cerrando: " + (timeFinal-timeIni)/1000);
		}catch(Exception e){
			fail("Exceptión inesperada: " + e);
		}
	}

	@Test
	public void testBusquedaEnPrueba(){
		String sql1 = "select * from prueba order by cadena1";
		String sql2 = "select * from prueba order by cadena2";
		
		try{
			long timeIni1 = System.currentTimeMillis();
			Conexion db=Broker.get().getConnectionSeleccion();
			PreparedStatement p=db.prepareStatement(sql1);
			ResultSet r = p.executeQuery();
			r.next();
			long timeFinal1 = System.currentTimeMillis();
			System.out.println("Tiempo con índice: " + (timeFinal1-timeIni1)/100);
			
			long timeIni2 = System.currentTimeMillis();
			Conexion db2=Broker.get().getConnectionSeleccion();
			PreparedStatement p2=db2.prepareStatement(sql2);
			ResultSet r2 = p2.executeQuery();
			r2.next();
			long timeFinal2 = System.currentTimeMillis();
			System.out.println("Tiempo sin índice: " + (timeFinal2-timeIni2)/100);
		}catch (Exception e){
			fail("...");
		}
	}
	
	
	//@Test
	public void testInsertarEnPrueba(){
		String sql = "insert into prueba (cadena1, cadena2) values (?,?)";
		
		try{
			long timeIni = System.currentTimeMillis();
			Conexion db=Broker.get().getConnectionInsercion();
			PreparedStatement p=db.prepareStatement(sql);
			for(int i=0; i<300; i++){
				String cadenaAleatoria = getCadenaAleatoria();
				p.setString(1, cadenaAleatoria);
				p.setString(2, cadenaAleatoria);
				p.executeUpdate();
			}
			db.close();
			long timeFinal = System.currentTimeMillis();
			System.out.println("Tiempo de inserción: " + (timeFinal-timeIni)/1000);
		}catch(Exception e){
			fail("Exceptión inesperada: " + e);
		}
	}

	private String getCadenaAleatoria() {
		String r="";
		int longitud = new Random().nextInt(255)+1;
		for(int i=0; i<longitud; i++){
			char c=(char) (new Random().nextInt(60)+65);
			r+=c;
		}
		return r;
	}
}
