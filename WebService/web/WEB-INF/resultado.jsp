<%-- 
    Document   : resultado
    Created on : 20-oct-2016, 16:34:03
    Author     : Leonardo
--%>

<%@page import="org.json.simple.JSONArray"%>
<%@page import="java.io.StringWriter"%>
<%@page import="org.json.simple.JSONObject"%>
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

        <%ArrayList alumno = (ArrayList) request.getAttribute("Listaalumno"); %>
        <%ArrayList tutorias = (ArrayList) request.getAttribute("Listatutorias"); %>
        <%ArrayList asignaturas = (ArrayList) request.getAttribute("Listaasignaturas"); %>
        <%String xml = (String) request.getAttribute("Listaxml"); %>
        <%String[] alumnorow;%>
        <%JSONArray resularr = (JSONArray) request.getAttribute("Listaarrayjson"); %>

        <h1>Datos del alumno</h1>
        <p>
            Alumno:
            <%alumnorow = (String[]) alumno.get(0);%>
            <%out.print(alumnorow[0]);%>

        </p>
        <p>
            Tutorias:
            <% for (int i = 0; i < tutorias.size(); i++) {
                    alumnorow = (String[]) tutorias.get(i);
                    for (int k = 0; k < alumnorow.length; k++) {
                        out.print(alumnorow[k]);
                        out.print(",");
                    }
                }
            %>

        </p>
        <p>
            Asignaturas:
            <% for (int i = 0; i < asignaturas.size(); i++) {
                    alumnorow = (String[]) asignaturas.get(i);
                    for (int k = 0; k < alumnorow.length; k++) {
                        //if (alumnorow[k] != null) {
                        out.print(alumnorow[k]);
                        out.print(",");
                        //}
                    }
                }
            %> 
            <%
                StringWriter out2 = new StringWriter();
                resularr.writeJSONString(out2);

                String jsonText
                        = out2.toString();
            %>
        </p>
    <p2  id="json">
        JSON<br>

    </p2>

    <p style="display:none;">
        Aqui va en json sin javascript
    </p>       
    <p3 id="textjson" style="display:none;">
        <%out.print(jsonText);%> 

    </p3>
    <p4>
        <br>
        XML GENERADO:
        <% 
           
                //out.print("<pre lang='xml'>" + xml+xml.length() + "</pre>");
                xml=xml.replaceAll("<", "&lt");
                xml=xml.replaceAll(">", "&gt");
                out.print("<pre lang='xml'>" + xml + "</pre>");
        %> 
    </p4>
    <script>


        var text = document.getElementById("textjson").innerHTML;
        var obj = JSON.parse(text);


        for (var i = 0; i < obj.length; i++) {
            if (obj[i].Nombre != null) {
                document.getElementById("json").innerHTML = document.getElementById("json").innerHTML + obj[i].Nombre + "<br>";
            }
            if (obj[i].Tutoria != null) {
                document.getElementById("json").innerHTML = document.getElementById("json").innerHTML + obj[i].Tutoria + "<br>";
            }
            if (obj[i].Asignatura != null) {
                document.getElementById("json").innerHTML = document.getElementById("json").innerHTML + obj[i].Asignatura + "<br>";
            }
        }


    </script>

</body>
</html>