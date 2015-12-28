<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.uclm.esi.tysweb2015.dao.*, java.sql.*, org.json.*" %>

<%
String sComunidad = request.getParameter("comunidad");
int comunidad = Integer.parseInt(sComunidad);

Conexion db=Broker.get().getConnectionSeleccion();
String sql = null;
PreparedStatement ps;
if(comunidad == -1){
	sql ="select id, nombre from ubicaciones where tipo='Provincia' order by nombre";
	ps=db.prepareStatement(sql);
}else{
	sql ="select id, nombre from ubicaciones where tipo='Provincia' and idPadre=? order by nombre";
	ps=db.prepareStatement(sql);
	ps.setInt(1, comunidad);
}

JSONArray jsa=new JSONArray();
ResultSet rs=ps.executeQuery();
while (rs.next()) {
	JSONObject jso = new JSONObject();
	jso.put("id", rs.getInt(1));
	jso.put("nombre", rs.getString(2));
	jsa.put(jso);
}
%>
<%= jsa.toString() %>