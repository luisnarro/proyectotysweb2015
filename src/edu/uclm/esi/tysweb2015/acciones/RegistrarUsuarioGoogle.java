package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class RegistrarUsuarioGoogle extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String email;
	private String resultado;
	
	public String execute() {
		try {
			Gestor gestor=Gestor.get();
			Usuario usuario = gestor.existeUsuarioGoogle(email);
			if(usuario == null){
				usuario = gestor.registrarUsuarioGoogle(email);
			}
			
			ServletActionContext.getRequest().getSession().setAttribute("usuario", usuario);
			this.resultado="OK";
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
			result.put("usuario", this.email);
			
			return result.toString();
		} catch (JSONException e) {
			return "{\"resultado\" : \"error\", \"mensaje\" : \"Error\"}";
		}
	}

	public void setEmail(String email) {this.email=email;}
	
}
