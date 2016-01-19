<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*,java.sql.*, org.json.*, edu.uclm.esi.tysweb2015.dominio.*,
java.util.Hashtable, java.util.Set" %>

<%

int idAnuncio=Integer.parseInt(request.getParameter("idAnuncio"));
Conexion db=Broker.get().getConnectionSeleccion();

String sql ="select a.descripcion, a.fechaDeAlta, u.nombre from anuncios a, usuarios u where a.id=? AND a.idAnunciante = u.id order by a.fechaDeAlta DESC";

PreparedStatement ps=db.prepareStatement(sql);
ps.setInt(1, idAnuncio);
JSONObject result=new JSONObject();
try{
	ResultSet rs=ps.executeQuery();
	while (rs.next()) {
		Anuncio anuncio = new Anuncio(idAnuncio, rs.getString(1));
		anuncio.recuperarFotos();

		result.put("fecha", rs.getInt(2));
		result.put("descripcion", rs.getString(1));
		result.put("nombreAnunciante", rs.getString(3));
		
		//Todas las fotos
		JSONArray fotosJSA = new JSONArray();

		Hashtable<String, String> fotos = anuncio.getFotos();
		Set<String> keys = fotos.keySet();
		for(String key: keys){
			JSONObject jso=new JSONObject();
	        jso.put("foto",fotos.get(key));
	        fotosJSA.put(jso);
	    }
		result.put("fotos", fotosJSA);
	}
} catch(Exception e){
	throw e;
} finally{
	db.close();
}

%>
<%= result.toString() %>