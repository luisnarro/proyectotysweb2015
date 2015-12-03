package edu.uclm.esi.tysweb2015.acciones;

import java.util.Arrays;

import com.opensymphony.xwork2.ActionSupport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public class GoogleLogin extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private String tokenRecibido;
	private String resultado;
	
	public String execute() {
		try {
			JsonFactory jsonFactory = new JacksonFactory();
			HttpTransport transport = new NetHttpTransport();
			
			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
		    .setAudience(Arrays.asList("353845812738-6tjfl6b6crcuri7mrfqugb4dte5gbsnb"))
		    .build();
			
			this.resultado="OK";
			return SUCCESS;
		} catch (Exception e) {
			this.resultado=e.getMessage();
			return ERROR;
		}
	}
	
	public String getResultado(){
		return this.resultado;
	}

	public void setTokenRecibido(String email) {this.tokenRecibido=email;}
}