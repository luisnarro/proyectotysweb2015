package edu.uclm.esi.tysweb2015.dominio;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import edu.uclm.esi.tysweb2015.dao.DAOAnuncio;

public class Anuncio {
	private int idAnuncio;
	private String descripcion;
	private int fechaAlta;
	private int idCategoria;
	private int idAnunciante;
	private Hashtable<String,String> fotos = new Hashtable<String,String>();
	
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

	public Anuncio(int idAnuncio, int idUsuario) {
		this.idAnuncio = idAnuncio;
		this.idAnunciante = idUsuario;
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
	public Hashtable<String, String> getFotos() {
		return fotos;
	}
	public String getFirstFoto(){
		String result = null;
		Boolean bo = true;
		Enumeration<String> ides = this.fotos.keys();
		while (ides.hasMoreElements() && bo){
			result = this.fotos.get(ides.nextElement());
			bo = false;
		}
		return result;
	}
	public void addFotoAlArray(String key, String value){
		this.fotos.put(key, value);
	}

	public void addFoto(String identificador, String uploadContentType) throws SQLException, Exception {
		int result;
		result = DAOAnuncio.addFoto(this, identificador, uploadContentType);
		String formato = uploadContentType.split("/")[1];
		this.fotos.put(Integer.toString(result), identificador+"."+formato);
	}

	public Anuncio recuperar() throws SQLException, Exception {
		DAOAnuncio.recuperar(this);
		return this;
	}

	public void recuperarFotos() throws SQLException, Exception {
		DAOAnuncio.recuperarFotos(this);		
	}

	public ArrayList<Anuncio> buscarAnuncios(String palabras, int categoria,
			int provincia, int orden) throws SQLException, Exception {
		return DAOAnuncio.buscarAnuncios(palabras, categoria, provincia, orden);
	}

}
