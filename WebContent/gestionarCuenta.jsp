<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dominio.Usuario, org.json.*, java.sql.*" %>

<%
	Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
	JSONObject jso=new JSONObject();
	
	if (usuario.getApellido1() != null && (usuario.getTipoDeOAuth() == 1)){
		jso.put("resultado", "OK");
		jso.put("tipo", "usuarioNormal");
		jso.put("idUsuario", usuario.getIdUusuario());
		jso.put("nombre", usuario.getNombre());
		jso.put("apellido1", usuario.getApellido1());
		jso.put("apellido2", usuario.getApellido2());
		jso.put("telefono", usuario.getTelefono());
		jso.put("email", usuario.getEmail());
		jso.put("anuncios", usuario.getAnuncios());
		jso.put("deseos", usuario.getDeseos());
	}else{
		if (usuario.getTipoDeOAuth() > 1){
			jso.put("resultado", "OK");
			jso.put("tipo", "usuarioGoogle");
			jso.put("info", "La información de los usuarios de Google+ no está disponible.");
			jso.put("idUsuario", usuario.getIdUusuario());
			jso.put("anuncios", usuario.getAnuncios());
		}else{
			jso.put("resultado", "Error");
			jso.put("tipo", "");
		}
	}
%>

<%= jso.toString() %>