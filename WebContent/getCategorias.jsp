<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*, java.sql.*, org.json.*" %>

<%
Conexion db=Broker.get().getConnectionSeleccion();
String sql ="select id, nombre from categorias order by nombre";

PreparedStatement ps=db.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ResultSet rs=ps.executeQuery();
while (rs.next()) {
	JSONObject jso = new JSONObject();
	jso.put("id", rs.getInt(1));
	jso.put("nombre", rs.getString(2));
	jsa.put(jso);	
}
db.close();
%>
<%= jsa.toString() %>