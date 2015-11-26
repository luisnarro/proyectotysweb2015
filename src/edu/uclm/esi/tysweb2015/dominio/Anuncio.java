package edu.uclm.esi.tysweb2015.dominio;

import java.io.File;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;

public class Anuncio {
	private int idAnuncio;
	private String descripcion;
	private int fechaAlta;
	private int idCategoria;
	private int idAnunciante;
	
	public Anuncio(){
		
	}
	
	public Anuncio(String descripcion, int idCategoria, int idAnunciante) throws SQLException, Exception{
		this.descripcion = descripcion;
		this.idCategoria = idCategoria;
		this.idAnunciante = idAnunciante;
	}
	
	public Anuncio(int idAnuncio, String descripcion) {
		this.idAnuncio = idAnuncio;
		this.descripcion = descripcion;
	}

	public void insert() throws Exception {
		DAOAnuncio.insert(this);		
	}
	
	public int getIdAnuncio() {
		return idAnuncio;
	}
	public void setIdAnuncio(int idAnuncio) {
		this.idAnuncio = idAnuncio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(int fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdAnunciante() {
		return idAnunciante;
	}
	public void setIdAnunciante(int idAnunciante) {
		this.idAnunciante = idAnunciante;
	}

	public int addFoto(String identificador, String uploadContentType) throws SQLException, Exception {
		return DAOAnuncio.addFoto(this, identificador, uploadContentType);
	}

}
