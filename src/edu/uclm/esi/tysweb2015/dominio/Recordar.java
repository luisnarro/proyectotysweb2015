package edu.uclm.esi.tysweb2015.dominio;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.SQLException;

import edu.uclm.esi.tysweb2015.dao.DAORecordar;

public class Recordar {
	public String email;
	public String idUsuario;
	public String token;
	//public int idRecuperar;
	
	public Recordar(String email){
		this.email = email;
		this.idUsuario = null;
	}

	public Recordar() {}

	public void comprobarEmail() throws SQLException, Exception {
		DAORecordar.comprobarUsuario(this);
		if(this.idUsuario != null){
			procesoCambioPwd();
		}
	}

	private void procesoCambioPwd() throws SQLException, Exception {
		generarToken();
		DAORecordar.guardarToken(this);
		enviarEmail();
	}

	private void enviarEmail() throws Exception {
		Mail.enviarMail(this.email, this.token);
	}

	private void generarToken() {
		SecureRandom random = new SecureRandom();
		String result = new BigInteger(130, random).toString(32);
		this.token = result;
	}

	public void comprobarToken() throws SQLException, Exception {
		DAORecordar.comprobarToken(this);
	}

	public void setToken(String token) {
		this.token=token;
	}

	public String getToken() {
		return this.token;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void eliminarToken() throws SQLException, Exception {
		DAORecordar.eliminarToken(this.token);
	}

	public void updatePwd(String pwd1) throws SQLException, Exception {
		DAORecordar.updatePwd(pwd1, this.idUsuario);
	}
}