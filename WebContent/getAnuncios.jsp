<%@page import="jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode"%>
<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*,java.sql.*, org.json.*, edu.uclm.esi.tysweb2015.dominio.*" %>

<%

int idCategoria=Integer.parseInt(request.getParameter("idCategoria"));
Conexion db=Broker.get().getConnectionSeleccion();

String sql ="select id, descripcion from anuncios where idCategoria=? order by fechaDeAlta DESC";

PreparedStatement ps=db.prepareStatement(sql);
ps.setInt(1, idCategoria);
JSONArray jsa=new JSONArray();
try{
	ResultSet rs=ps.executeQuery();
	while (rs.next()) {
		Anuncio anuncio = new Anuncio(rs.getInt(1), rs.getString(2));
		anuncio.recuperarFotos();
		JSONObject jso = new JSONObject();
		jso.put("id", rs.getInt(1));
		jso.put("descripcion", rs.getString(2));
		jso.put("foto", anuncio.getFirstFoto());
		jsa.put(jso);	
	}
}catch(Exception e){
	throw e;
}finally{
	db.close();
}
%>
<%= jsa.toString() %>