package edu.uclm.esi.tysweb2015.dominio;

import java.sql.SQLException;

public class Gestor {
	private static Gestor yo;
	
	private Gestor(){
		
	}

	public static Gestor get() {
		if (yo==null){
			yo = new Gestor();
		}
		return yo;
	}

	public void registrar(String email, String nombre, String apellido1,
			String apellido2, String telefono, String pwd1, int ubicacion) throws Exception {
		
		Usuario usuario = new Usuario(email, nombre, apellido1, apellido2, telefono, pwd1, ubicacion);
		usuario.insert(1);
	}

	public Usuario identificar(String email, String pwd) throws Exception {
		Usuario usuario = new Usuario(email, pwd);
		return usuario;
	}

	public Anuncio nuevoAnuncio(String descripcion, int idCategoria,
			int idAnunciante) throws SQLException, Exception {
		Anuncio anuncio = new Anuncio(descripcion, idCategoria, idAnunciante);
		anuncio.insert();
		return anuncio;
	}

	public void recuperarAunucios(Usuario usuario) throws SQLException, Exception {
		usuario.recuperarAnuncios();
	}

	public boolean emailCorrecto(String email) throws SQLException, Exception {
		boolean result = false;
		Recordar opRecordar = new Recordar(email);
		opRecordar.comprobarEmail();
		if(opRecordar.idUsuario != null)
			result = true;
		return result;
	}

	public Recordar procesoCambio(String token, String pwd) throws SQLException, Exception {
		Recordar opRecordar = new Recordar();
		opRecordar.setToken(token);
		opRecordar.comprobarToken();
		opRecordar.comprobarFecha();
		opRecordar.updatePwd(pwd);
		opRecordar.eliminarToken();
		return opRecordar;
		
	}

	public Usuario registrarUsuarioGoogle(String email) throws Exception {
		Usuario usuario = new Usuario(email);
		usuario.insert(2);
		return usuario;
	}

	public Usuario existeUsuarioGoogle(String email) throws SQLException, Exception {
		Usuario result = null;
		Usuario usuario = new Usuario(email);
		result = usuario.existeUsuarioGoogle();
		return result;
	}

	public Anuncio recuperarAnuncioUsuario(int idAnuncio, int idUsuario) throws SQLException, Exception {
		Anuncio result;
		Anuncio anuncio = new Anuncio(idAnuncio, idUsuario);
		result = anuncio.recuperar();
		return result;
		
	}

}
