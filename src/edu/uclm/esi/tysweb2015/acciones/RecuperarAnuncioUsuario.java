package edu.uclm.esi.tysweb2015.acciones;

import java.util.Hashtable;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class RecuperarAnuncioUsuario extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private int idAnuncio;
	private int idUsuario;
	private Anuncio anuncio;
	private String resultado;
	
	public String execute(){
		try{
			Gestor gestor = Gestor.get();
			this.anuncio = gestor.recuperarAnuncioUsuario(idAnuncio, idUsuario);
			anuncio.recuperarFotos();
			return SUCCESS;
		}catch(Exception e){
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	
	public String getResultado() {
		JSONObject result = new JSONObject();
		try {
			result.put("resultado", "OK");
			result.put("idAnuncio", this.anuncio.getIdAnuncio());
			result.put("descripcion", this.anuncio.getDescripcion());
			result.put("categoria", this.anuncio.getIdCategoria()); //Cambiar; enviar el nombre de la categoría.
			JSONArray fotosJSA = new JSONArray();
			JSONArray idesJSA = new JSONArray();
			Hashtable<String, String> fotos = this.anuncio.getFotos();
			Set<String> keys = fotos.keySet();
			for(String key: keys){
				JSONObject jso=new JSONObject();
	            jso.put("foto",fotos.get(key));
	            fotosJSA.put(jso);
	        }
			result.put("fotos", fotosJSA);
			
			for (String key: keys){
				JSONObject jso=new JSONObject();
	            jso.put("idFoto", key);
	            idesJSA.put(jso);
			}
			result.put("idsFotos", idesJSA);
			
		} catch (Exception e) {
			this.resultado="Error";
		}
		return result.toString();
	}
	
	public void setIdAnuncio(int idAnuncio){
		this.idAnuncio = idAnuncio;
	}
	
	public void setIdUsuario(int idUsuario){
		this.idUsuario = idUsuario;
	}

}
