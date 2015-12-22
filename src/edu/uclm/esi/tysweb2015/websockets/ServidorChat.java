package edu.uclm.esi.tysweb2015.websockets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

@ServerEndpoint("/servidorChat")
public class ServidorChat {
	static Set<Session> users=Collections.synchronizedSet(new HashSet<Session>());
	static Hashtable<String, ChatUser> chatUsers = new Hashtable<>();
	
	@OnOpen
	public void abrirConexion(Session session){
		users.add(session);
		System.out.println("Se ha abierto una conexión");
	}
	
	@OnMessage
	public void mensajeRecibido(String msg, Session session) throws JSONException{
		System.out.println("Se ha recibido: " + msg);
		JSONObject jso = new JSONObject(msg);
		try{
			if (jso.get("tipo").equals("identificacion")){
				String login = jso.getString("texto");
				ChatUser chatUser = new ChatUser(login, session);
				chatUsers.put(login, chatUser);
			}else if (jso.get("tipo").equals("mensajeUsuario")){
				String mensaje = jso.getString("texto");
				//Redirigir o almacenar el mensaje para que el usuario Administrador lo lea y conteste.
			}
		}catch(JSONException e){
			
		}
	}

	@OnClose
	public void close(Session session) {
		users.remove(session);
	}
}
