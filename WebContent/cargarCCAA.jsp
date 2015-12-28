<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*,java.sql.*, org.json.*" %>

<%
Conexion db=Broker.get().getConnectionSeleccion();
String sql ="select id, nombre from ubicaciones where tipo='Comunidad autónoma' order by nombre";

PreparedStatement ps=db.prepareStatement(sql);
JSONArray jsa=new JSONArray();
ResultSet rs=ps.executeQuery();
while (rs.next()) {
	//String par = "{\"id\" : " + rs.getInt(1) + ", " + "\"nombre\" : \"" + rs.getString(2) + "\"}";
	//jsa.put(par);
	JSONObject jso = new JSONObject();
	jso.put("id", rs.getInt(1));
	jso.put("nombre", rs.getString(2));
	jsa.put(jso);	
}
db.close();
%>
<%= jsa.toString() %>