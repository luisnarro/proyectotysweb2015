package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;
import edu.uclm.esi.tysweb2015.dominio.Anuncio;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;
import com.opensymphony.xwork2.ActionSupport;

public class NuevoAnuncio extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private int categoria;
	private String resultado;
	
	public String execute() {
		try {
			Usuario usuario = (Usuario) ServletActionContext.getRequest().getSession().getAttribute("usuario");
			int idAnunciante = usuario.getIdUusuario();
			Gestor gestor=Gestor.get();
			Anuncio anuncio = gestor.nuevoAnuncio(descripcion, categoria, idAnunciante);
			usuario.setAnuncio(anuncio);
			this.resultado="OK";
			return SUCCESS;
		} catch (Exception e) {
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	
	/*public JSONObject getResultado() throws JSONException{
		JSONObject jso = new JSONObject();
		jso.put("mensaje", this.resultado);
		return jso;
	}*/
	
	public String getResultado(){
		return this.resultado;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
}
