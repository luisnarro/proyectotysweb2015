<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%
String sProvincia = request.getParameter("Provincia");
int provincia = Integer.parseInt(sProvincia);

Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";
Connection db=DriverManager.getConnection(url, "selectorTSW2015", "");
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
%>
<%= jsa.toString() %>