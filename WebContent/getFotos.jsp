<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dominio.Anuncio, org.json.*, java.sql.*, java.util.Hashtable, java.util.Set" %>

<%
	Anuncio anuncio = (Anuncio)request.getSession().getAttribute("anuncio");
	JSONObject result = new JSONObject();
	
	if (anuncio.getIdAnuncio() > 0){
		Hashtable<String,String> anuncios = anuncio.getFotos();
		Set<String> keys = anuncios.keySet();
		String formato = "";
		JSONArray jsa = new JSONArray();
		result.put("resultado", "OK");
		for(String key: keys){
			JSONObject jso=new JSONObject();
			formato = anuncios.get(key).split("/")[1];
            jso.put("anuncio",key+"."+formato);
            jsa.put(jso);
        }
		result.put("anuncios", jsa);
	}else{
		result.put("resultado", "Error");
		result.put("tipo", "");
	}
%>

<%= result.toString() %>