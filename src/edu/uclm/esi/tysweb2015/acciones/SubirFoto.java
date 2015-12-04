package edu.uclm.esi.tysweb2015.acciones;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;

public class SubirFoto extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String uploadContentType;
	private String tmpFileName;
	private String resultado;
	private File upload;
	
	public String execute(){
		try{
			String tmpFolder=System.getProperty("java.io.tmpdir");
			String path = "C:\\Users\\LuisNarro\\Documents\\eclipseWTPworkspace\\proyectoTySW\\WebContent\\images\\";
			//String tmpFolder = ServletActionContext.getServletContext().getRealPath("/images");
			//String tmp = ServletActionContext.getServletContext().getContextPath();
			
			String rnd = generarIdFoto();
			String formato = "."+uploadContentType.split("/")[1];
			this.tmpFileName = path + rnd;
			File theFile = new File(tmpFileName + formato);
			FileUtils.copyFile(this.upload, theFile);
			
			//Recuperar el anuncio de la sesión (se ha puesto al subir el anuncio)
			Anuncio anuncio = (Anuncio) ServletActionContext.getRequest().getSession().getAttribute("anuncio");
			
			//Añadir la foto al anuncio mediante un método (anuncio.addFoto(theFile, this.uploadContentType))
			anuncio.addFoto(rnd, this.uploadContentType);
			
			return SUCCESS;
		}catch(Exception e){
			this.resultado=e.getMessage();
			return ERROR;
		}
	}

	public String getResultado() {
		JSONObject result=new JSONObject();
		try {
			result.put("tipo", "OK");
		} catch (JSONException e) {
			return "Error";
		}
		
		return result.toString();
	}

	public void setFoto(File upload) {
		this.upload = upload;
	}

	public void setFotoContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	private String generarIdFoto(){
		UUID uuid = UUID.randomUUID();
		ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
	    bb.putLong(uuid.getMostSignificantBits());
	    bb.putLong(uuid.getLeastSignificantBits());
	    String result = Base64.encodeBase64URLSafeString(bb.array());
	    //System.out.println("Java UUID: " + uuid +", en Base64: " + result);
	    return result;
	}
}
