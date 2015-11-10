package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class RecuperarAnuncios {
	private String resultado;
	
	public String execute() {
		try {
			Gestor gestor=Gestor.get();
			ServletActionContext.getRequest().getSession().getAttributeNames();
			Usuario usuario = (Usuario)ServletActionContext.getRequest().getSession().getAttribute("usuario");
			//gestor.recuperarAunucios(usuario);
			//this.resultado=usuario.getAnuncios().toString();
			this.resultado = "OK";
			return ActionSupport.SUCCESS;
		} catch (Exception e) {
			this.resultado=e.getMessage();
			return ActionSupport.ERROR;
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
}
