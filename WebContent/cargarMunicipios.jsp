<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*,java.sql.*, org.json.*" %>

<% response.setHeader("Access-Control-Allow-Origin", "*"); %>

<%
String sProvincia = request.getParameter("Provincia");
int provincia = Integer.parseInt(sProvincia);

Conexion db=Broker.get().getConnectionSeleccion();
String sql ="select id, nombre from ubicaciones where tipo='Municipio' and idPadre=? order by nombre";

PreparedStatement ps=db.prepareStatement(sql);
ps.setInt(1, provincia);
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