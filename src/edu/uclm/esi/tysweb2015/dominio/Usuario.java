package edu.uclm.esi.tysweb2015.dominio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.uclm.esi.tysweb2015.dao.DAOUsuario;

public class Usuario {

	private int idUsuario;
	private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String pwd1;
	private int idUbicacion;
	//private Connection bd;
	private int tipoDeOAuth;
	private ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
	private ArrayList<Deseo> deseos = new ArrayList<Deseo>();
	
	public Usuario() {
		
	}
	
	public Usuario(String email, String nombre, String apellido1,
			String apellido2, String telefono, String pwd1, int ubicacion) {
		this.email=email;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.telefono=telefono;
		this.pwd1=pwd1;
		this.idUbicacion=ubicacion;
	}
	
	public Usuario(String email, String pwd) throws SQLException, Exception {
		DAOUsuario.identificar(this, email, pwd);
		recuperarAnuncios();
		recuperarDeseos();
	}

	public Usuario(String email) {
		this.email = email;
	}

	public void insert(int... tipoDeOAuth) throws Exception {
		this.tipoDeOAuth = tipoDeOAuth[0];
		DAOUsuario.insert(this, tipoDeOAuth);		
	}
	
	public void recuperarAnuncios() throws SQLException, Exception {
		DAOUsuario.recuperarAnuncios(this);
	}
	
	public void recuperarDeseos() throws SQLException, Exception {
		this.deseos.clear();
		DAOUsuario.recuperarDeseos(this);
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setPwd1(String pwd1) {
		this.pwd1 = pwd1;
	}

	public void setIdUbicacion(int idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	
	public void setIdUsuario(int id) {
		this.idUsuario=id;
	}
	
	public void setTipoDeOAuth(int tipoOAuth) {
		this.tipoDeOAuth=tipoOAuth;
		
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getPwd() {
		return pwd1;
	}

	public int getIdUbicacion() {
		return idUbicacion;
	}
	
	public int getIdUusuario() {
		return idUsuario;
	}
	
	public int getTipoDeOAuth(){
		return tipoDeOAuth;
	}
	
	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}

	/*
	 *	public void setConnection(Connection bdUsuario) {
	 *		this.bd=bdUsuario;
	 *	}
	 */
	

	public ArrayList<Deseo> getDeseos() {
		return deseos;
	}

	public void addDeseo(Deseo deseo) {
		this.deseos.add(deseo);
	}

	public void setAnuncio(Anuncio anuncio){
		Anuncio anuncioMin = new Anuncio(anuncio.getIdAnuncio(), anuncio.getDescripcion());
		this.anuncios.add(anuncioMin);
	}

	public Usuario existeUsuarioGoogle() throws SQLException, Exception {
		Usuario result = null;
		if(DAOUsuario.existeUsuarioGoogle(this)){
			result = this;
		}
		recuperarAnuncios();
		return result;
	}

}
