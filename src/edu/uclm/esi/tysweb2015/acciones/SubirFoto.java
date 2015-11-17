package edu.uclm.esi.tysweb2015.acciones;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

import edu.uclm.esi.tysweb2015.dominio.Anuncio;

public class SubirFoto extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private File foto;
	private String uploadContentType;
	private String tmpFileName;
	private String resultado;
	private File upload;
	
	public String execute(){
		try{
			String tmpFolder=System.getProperty("java.io.tmpdir");
			int rnd = Math.abs(new Random().nextInt());
			this.tmpFileName = tmpFolder + rnd;
			File theFile = new File(tmpFileName);
			FileUtils.copyFile(upload, theFile);
			//Recuperar el anuncio de la sesión (se ha puesto al subir el anuncio)
			//Añadir la foto al anuncio mediante un método (anuncio.addFoto(theFile, this.uploadContentType))
			
			this.resultado="OK";
			return SUCCESS;
		}catch(Exception e){
			this.resultado=e.getMessage();
			return ERROR;
		}
	}

	public String getResultado() {
		return resultado;
	}

	public void setFoto(File foto) {
		this.foto = foto;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
}
