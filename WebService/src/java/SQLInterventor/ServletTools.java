/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLInterventor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class ServletTools {
//Sense JSP

protected void doGetHtml(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 response.setContentType("text/html");
 PrintWriter pw = response.getWriter();
 pw.println("<h1>Hello World!</h1>");
}


//Amb JSP

protected void doGetJSP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 RequestDispatcher a = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
 a.forward(request, response);
}

//Per enviar objectes des de Servlet a JSP:
//
//request.setAttribute("ObjectName",object);  //abans de Dispatcher
//
//
//al .jsp:
//
//.<%ObjectClass myObject= (ObjectClass)request.getAttribute("ObjectName"); %>
//<%
////Do whatever you want in java (server side)
//myObject.doWhatever()
//%>
//
////Do whatever you want in client side (xml, html, css, js,...)
//<html>
//...
}
