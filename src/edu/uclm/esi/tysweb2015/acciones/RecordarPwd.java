package edu.uclm.esi.tysweb2015.acciones;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class RecordarPwd {
	private String email;
	private String resultado;
	
	public String execute() {
		try {
			Gestor gestor = Gestor.get();
			if(!gestor.emailCorrecto(email))
				throw new Exception("El email no es válido.");
			
			this.resultado="Mensaje enviado. Verifica tu buzón de correo.";
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
