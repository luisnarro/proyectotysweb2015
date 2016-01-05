package edu.uclm.esi.tysweb2015.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class DAOUsuario {

	public static void insert(Usuario usuario, int... tipoDeOAuth) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionInsercion();
		
		try{
			if (tipoDeOAuth[0] == 1){
				String sql="{call insertarUsuario (?, ?, ?, ?, ?, ?, ?, ?)}";
				CallableStatement cs=bd.prepareCall(sql);
				cs.setString(1, usuario.getEmail());
				cs.setString(2, usuario.getPwd());
				cs.setString(3, usuario.getNombre());
				cs.setString(4, usuario.getApellido1());
				cs.setString(5, usuario.getApellido2());
				cs.setString(6, usuario.getTelefono());
				cs.setInt(7, usuario.getIdUbicacion());
				cs.registerOutParameter(8, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				String exito=cs.getString(8);
				if (exito!=null && !(exito.equals("OK")))
					throw new SQLException(exito);
			} else if(tipoDeOAuth[0] == 2){
				String sql="{call insertarUsuarioOAuth (?, ?, ?)}";
				CallableStatement cs=bd.prepareCall(sql);
				cs.setString(1, usuario.getEmail());
				cs.setInt(2, tipoDeOAuth[0]);
				cs.registerOutParameter(3, java.sql.Types.VARCHAR);
				cs.executeUpdate();
				String exito=cs.getString(3);
				if (exito!=null && !(exito.equals("OK")))
					throw new SQLException(exito);
			}
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static void identificar(Usuario usuario, String email, String pwd) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT id, nombre, apellido1, apellido2, telefono, idUbicacion, accesoGoogle FROM Usuarios where email=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setString(1, email);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				int id = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellido1 = rs.getString(3);
				String apellido2 = rs.getString(4);
				String telefono = rs.getString(5);
				int idubicacion = rs.getInt(6);
				int tipoOAuth = rs.getInt(7);
				String userName = "tysweb2015" + id;
				
				if (Broker.get().existe(email, pwd)){
					usuario.setIdUsuario(id);
					usuario.setEmail(email);
					usuario.setNombre(nombre);
					usuario.setApellido1(apellido1);
					usuario.setApellido2(apellido2);
					usuario.setTelefono(telefono);
					usuario.setPwd1(pwd);
					usuario.setIdUbicacion(idubicacion);
					usuario.setTipoDeOAuth(tipoOAuth);
					//usuario = new Usuario(email, nombre, apellido1, apellido2, telefono, pwd, idubicacion);
					//usuario.setConnection(bdUsuario);
				}
			}else {
				throw new Exception("Login o contraseña incorrectos");
			}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static void recuperarAnuncios(Usuario usuario) throws SQLException, Exception {
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT id, fechaDeAlta, descripcion, idCategoria FROM Anuncios where idAnunciante=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setInt(1, usuario.getIdUusuario());
			ResultSet rs = p.executeQuery();
			//if(rs.first()){
				while(rs.next()) {
					int id = rs.getInt(1);
					int fecha = rs.getInt(2);
					String descripcion = rs.getString(3);
					int idCategoria = rs.getInt(4);
					Anuncio anuncio = new Anuncio();
					anuncio.setIdAnuncio(id);
					anuncio.setFechaAlta(fecha);
					anuncio.setDescripcion(descripcion);
					anuncio.setIdCategoria(idCategoria);
					anuncio.setIdAnunciante(usuario.getIdUusuario());
					
					usuario.setAnuncio(anuncio);
				}
			//}else {
				//throw new Exception("No hay anuncios para este usuario.");
			//}
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
	}

	public static boolean existeUsuarioGoogle(Usuario usuario) throws SQLException, Exception {
		boolean result = false;
		Conexion bd = Broker.get().getConnectionSeleccion();
		try{
			String sql= "SELECT id, accesoGoogle FROM Usuarios where email=?";
			PreparedStatement p = bd.prepareStatement(sql);
			p.setString(1, usuario.getEmail());
			ResultSet rs = p.executeQuery();
			boolean r = rs.next();
			if (r){
				result = true;
				usuario.setIdUsuario(rs.getInt(1));
				usuario.setTipoDeOAuth(rs.getInt(2));
			}
			
		}
		catch(Exception e){
			throw e;
		}
		finally{
			bd.close();
		}
		return result;
	}
}
