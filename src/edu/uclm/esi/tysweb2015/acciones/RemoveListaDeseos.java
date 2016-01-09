package edu.uclm.esi.tysweb2015.acciones;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Gestor;
import edu.uclm.esi.tysweb2015.dominio.Usuario;

public class RemoveListaDeseos extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private int anuncio;
	private String usuario;
	private String resultado;
	
	public String execute() {
		try {
			Gestor gestor = Gestor.get();
			if(gestor.comprobarListaDeseos(usuario, anuncio)){
				gestor.eliminarDeseo(usuario, anuncio);
				Usuario usuario = (Usuario) ServletActionContext.getRequest().getSession().getAttribute("usuario");
				usuario.recuperarDeseos();
				this.resultado="Anuncio eliminado de la lista correctamente";
			}else{
				this.resultado="Error al eliminar el anuncio de la lista";
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
