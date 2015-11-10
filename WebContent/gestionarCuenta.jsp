<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dominio.Usuario, org.json.*, java.sql.*" %>

<%
	Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
	JSONObject jso=new JSONObject();
	
	if (usuario.getApellido1() != null){
		jso.put("resultado", "OK");
		jso.put("nombre", usuario.getNombre());
		jso.put("apellido1", usuario.getApellido1());
		jso.put("apellido2", usuario.getApellido2());
		jso.put("telefono", usuario.getTelefono());
		jso.put("email", usuario.getEmail());
	}else{
		jso.put("resultado", "Error");
	}
%>

<%= jso.toString() %>