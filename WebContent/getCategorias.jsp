<%@ page language="java" contentType="application/json; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*, org.json.*" %>

<%
Class.forName("com.mysql.jdbc.Driver");
//String url="jdbc:mysql://alarcosj.esi.uclm.es:3306/tysweb2015";
String url="jdbc:mysql://localhost:3306/tysweb2015";
Connection db=DriverManager.getConnection(url, "selectorTSW2015", "selectorTSW2015");
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