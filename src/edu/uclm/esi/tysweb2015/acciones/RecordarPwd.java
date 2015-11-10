package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class RecordarPwd {
	private String email;
	private String resultado;
	
	public String execute() {
		try {
			this.resultado="OK";
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
	
	public void setEmail(String email) {
		this.email= email;
	}
	
	public String getResultado(){
		return this.resultado;
	}
}
