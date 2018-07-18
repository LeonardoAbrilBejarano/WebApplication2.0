<%-- 
    Document   : index
    Created on : 19-oct-2016, 0:43:07
    Author     : Leonardo
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>REQUEST DATA</title>
                <style>
            body {background-color: powderblue;}
            h1   {color: blue;}
</style>
    </head>
    <body>
        <%ArrayList Datos = (ArrayList) request.getAttribute("Listaalumnos"); %>
        <%String[] alumno;%>
        <form method="post" action="login">
            Eliga el alumno que desea consultar
            <select name="combobox" id="combobox">
                <% for(int i=0;i<Datos.size();i++){
                   alumno=(String[])Datos.get(i);
                %><option value="<%out.print(alumno[0]);%>"><%out.print(alumno[1]);%></option><%  
                }
                %>
            </select> 
            <input type="submit" value="login" />
        </form>
    </body>
</html>
