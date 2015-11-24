package edu.uclm.esi.tysweb2015.websockets;

import javax.websocket.Session;

public class ChatUser {

	private String login;
	private Session session;

	public ChatUser(String login, Session session) {
		this.login = login;
		this.session = session;
	}

}
