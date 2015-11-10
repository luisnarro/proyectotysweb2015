package edu.uclm.esi.tysweb2015.dominio;

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

}