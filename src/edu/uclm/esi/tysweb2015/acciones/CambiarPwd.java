package edu.uclm.esi.tysweb2015.acciones;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Recordar;

public class CambiarPwd extends ActionSupport{
	private String token;
	private String pwd1;
	private String pwd2;
	private String resultado;
	
	public String execute() {
		try {
			String error = validar();
			if(error!= null)
				throw new Exception(error);
			Gestor gestor=Gestor.get();
			
			Recordar cambioPwd = gestor.comprobarToken(token);
			
			// ¿eliminar y actualizar al mismo tiempo? (stored procedure)
			//cambioPwd.eliminarToken();
			cambioPwd.updatePwd(pwd1);
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
	
	private String validar() {
		if (this.pwd1==null || this.pwd2.length()==0)
			return "La password no puede estar vacía";
		if(!this.pwd1.equals(this.pwd2))
			return "Las passwords no coinciden";
		return null;
	}

	public void setToken(String token) {this.token=token;}
	public void setPwd1(String pwd1) {this.pwd1=pwd1;}
	public void setPwd2(String pwd2) {this.pwd2=pwd2;}
}
