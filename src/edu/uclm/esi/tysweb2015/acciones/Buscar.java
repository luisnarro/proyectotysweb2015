package edu.uclm.esi.tysweb2015.acciones;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class Buscar extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String palabras;
	private int categoria;
	private int provincia;
	private int orden;
	private String resultado;
	private ArrayList<Anuncio> anuncios;
	
	public String execute() {
		try {
			Gestor gestor=Gestor.get();
			this.anuncios = gestor.buscarAnuncios(palabras, categoria, provincia, orden);
			return SUCCESS;
		} catch (Exception e) {
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	
	public String getResultado(){
		JSONObject result = new JSONObject();
		try {
			result.put("resultado", "OK");
			
			JSONArray anunciosJSA = new JSONArray();
			for (Anuncio anuncio : this.anuncios){
				anuncio.recuperarFotos();
				JSONObject jso = new JSONObject();
				jso.put("id", anuncio.getIdAnuncio());
				jso.put("descripcion", anuncio.getDescripcion());
				jso.put("idCat", anuncio.getIdCategoria());
				jso.put("idAnunciante", anuncio.getIdAnunciante());
				jso.put("foto", anuncio.getFirstFoto());
				anunciosJSA.put(jso);
			}
			result.put("anuncios", anunciosJSA);
			
		} catch (Exception e) {
			this.resultado="Error";
		}
		return result.toString();
	}
	


	public void setPalabras(String palabras) {this.palabras=palabras;}
	public void setCategoria(int categoria) {this.categoria=categoria;}
	public void setProvincia(int provincia) {this.provincia=provincia;}
	public void setOrden(int orden) {this.orden=orden;}

}
