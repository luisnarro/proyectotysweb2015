package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class AddListaDeseos extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private int anuncio;
	private String usuario;
	private String resultado;
	
	public String execute() {
		try {
			Gestor gestor = Gestor.get();
			if(gestor.comprobarListaDeseos(usuario, anuncio)){
				this.resultado="Ya tienes este producto en tu lista de deseos.";
			}else{
				gestor.addListaDeseos(usuario, anuncio);
				Usuario usuario = (Usuario) ServletActionContext.getRequest().getSession().getAttribute("usuario");
				usuario.recuperarDeseos();
				this.resultado="A�adido a tu lista correctamente.";
			}
			
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

	public void setAnuncio(int anuncio) {this.anuncio=anuncio;}
	public void setUsuario(String usuario) {this.usuario=usuario;}
}
