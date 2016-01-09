package edu.uclm.esi.tysweb2015.dominio;

import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAODeseo;

public class Deseo {

	private String usuario;
	private int idUsuario;
	private int idAnuncio;
	private String descripcion;

	

	public Deseo(String usuario, int idAnuncio) {
		this.usuario = usuario;
		this.idAnuncio = idAnuncio;
	}

	public Deseo(int idUsuario, int idAnuncio) {
		this.idUsuario=idUsuario;
		this.idAnuncio=idAnuncio;
	}

	public boolean comprobarExiste() throws SQLException, Exception {
		return DAODeseo.comprobarExiste(this);
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getIdAnuncio() {
		return idAnuncio;
	}

	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}

	public void insertar() throws SQLException, Exception {
		DAODeseo.insertar(this);
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void eliminar() throws SQLException, Exception {
		DAODeseo.eliminar(this);
	}

}
