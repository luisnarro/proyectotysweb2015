package edu.uclm.esi.tysweb2015.dominio;

import javax.mail.PasswordAuthentication;

import javax.mail.Authenticator;

public class autenticador extends Authenticator {
	
	private String from;

	public autenticador(String from) {
		this.from = from;
	}

	public PasswordAuthentication get(){
		return new PasswordAuthentication(from, "tysweb2015");
	}
}
