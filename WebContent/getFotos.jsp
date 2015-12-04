<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dominio.Anuncio, org.json.*, java.sql.*, java.util.Hashtable, java.util.Set" %>

<%
	Anuncio anuncio = (Anuncio)request.getSession().getAttribute("anuncio");
	JSONObject result = new JSONObject();
	
	if (anuncio.getIdAnuncio() > 0){
		Hashtable<String,String> anuncios = anuncio.getFotos();
		Set<String> keys = anuncios.keySet();
		JSONArray jsa = new JSONArray();
		result.put("resultado", "OK");
		for(String key: keys){
			JSONObject jso=new JSONObject();
            jso.put("foto",anuncios.get(key));
            jsa.put(jso);
        }
		result.put("fotos", jsa);
	}else{
		result.put("resultado", "Error");
		result.put("tipo", "");
	}
%>

<%= result.toString() %>