<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*,java.sql.*, org.json.*" %>

<%

int idCategoria=Integer.parseInt(request.getParameter("idCategoria"));
Conexion db=Broker.get().getConnectionSeleccion();

String sql ="select id, descripcion from anuncios where idCategoria=? order by fechaDeAlta DESC";

PreparedStatement ps=db.prepareStatement(sql);
ps.setInt(1, idCategoria);
JSONArray jsa=new JSONArray();
ResultSet rs=ps.executeQuery();
while (rs.next()) {
	JSONObject jso = new JSONObject();
	jso.put("id", rs.getInt(1));
	jso.put("descripcion", rs.getString(2));
	jsa.put(jso);	
}
db.close();
%>
<%= jsa.toString() %>