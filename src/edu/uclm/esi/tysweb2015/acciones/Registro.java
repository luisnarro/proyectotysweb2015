package edu.uclm.esi.tysweb2015.acciones;

import com.opensymphony.xwork2.ActionSupport;
import edu.uclm.esi.tysweb2015.dominio.Gestor;

public class Registro extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String email;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String telefono;
	private String pwd1;
	private String pwd2;
	private int ubicacion;
	private String resultado;
	
	public String execute() {
		try {
			String error = validar();
			if(error!= null)
				throw new Exception(error);
			Gestor gestor=Gestor.get();
			gestor.registrar(email, nombre, apellido1, apellido2, telefono, pwd1, ubicacion);
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

	public void setEmail(String email) {this.email=email;}
	public void setNombre(String nombre) {this.nombre=nombre;}
	public void setApellido1(String apellido1) {this.apellido1=apellido1;}
	public void setApellido2(String apellido2) {this.apellido2=apellido2;}
	public void setTelefono(String telefono) {this.telefono=telefono;}
	public void setPwd1(String pwd1) {this.pwd1=pwd1;}
	public void setPwd2(String pwd2) {this.pwd2=pwd2;}
	public void setUbicacion(int ubicacion){this.ubicacion=ubicacion;}
	
}
