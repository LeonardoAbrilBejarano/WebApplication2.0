/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet2;

/**
 *
 * @author Leonardo
 */

import org.json.simple.JSONObject;
import FileWR.FileWR;
import JAXB.JABX;
import SQLInterventor.SQLInterventor;
import generated.Alumnos;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import org.json.simple.JSONArray;

public class Login extends HttpServlet {
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>PROCESO</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>PROCESO " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            this.procesoSQLpost(request,response);
        } catch (JAXBException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher a = request.getRequestDispatcher("/WEB-INF/resultado.jsp");
        a.forward(request, response);
        
    }
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.procesoSQLget(request);
        RequestDispatcher a = request.getRequestDispatcher("/WEB-INF/index.jsp");
        a.forward(request, response);
    }
    
    private void procesoSQLget(HttpServletRequest request){
        SQLInterventor sqli=new SQLInterventor();
        ArrayList alumnos=null;
        try {
            alumnos=sqli.mostrarContenido("Select * from alumne");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("Listaalumnos",alumnos);  
    }
    
    private void procesoSQLpost(HttpServletRequest request,HttpServletResponse response) throws IOException, JAXBException{
        //Get id alumno
        int id=Integer.parseInt((String) request.getParameter("combobox"));
        //Consulta sql+arraylist
        SQLInterventor sqli=new SQLInterventor();
        ArrayList alumno=null;
        ArrayList tutorias=null;
        ArrayList asignaturas=null;
        try {
            //asd=sqli.mostrarContenidoAlumnos("select alumne.nom nomal, tutoria.nom nomt, assignatura.nom noma from alumne INNER JOIN tutoriaalumne on alumne.codi=tutoriaalumne.codiAlumne INNER JOIN tutoria on tutoria.codi=tutoriaalumne.codiTutoria INNER JOIN assignatura on tutoria.codi=assignatura.codi where alumne.codi="+id+";");
            alumno=sqli.mostrarContenido("Select nom from alumne where codi="+id+";");
            tutorias=sqli.mostrarContenidoAlumnos("select nom from tutoria left join tutoriaalumne on tutoria.codi=tutoriaalumne.codiTutoria where tutoriaalumne.codiAlumne="+id+";");
            asignaturas=sqli.mostrarContenidoAlumnos("select assignatura.nom from assignatura inner join tutoria on assignatura.codi=tutoria.codiAssignatura inner join tutoriaalumne on tutoriaalumne.codiTutoria=tutoria.codi where tutoriaalumne.codiAlumne="+id+";");
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Get arraylist alumnos by jabx schema and data
        Alumnos al=null;
        File f=new File("Alumnoasignaturas.xml");
        try {
            al = JABX.generarPersonas(alumno,tutorias,asignaturas);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Generate xml and full string of xml
        JABX.objectToXml(al, f);
        String xml=this.getXMLdata(al);
        //Clean redundant data from asignaturas
        asignaturas=this.cleanRedundantData(asignaturas);
        //Setting normal data to jsp
        request.setAttribute("Listaalumno",alumno); 
        request.setAttribute("Listatutorias",tutorias);
        request.setAttribute("Listaasignaturas",asignaturas);
        //Generating json array
        JSONArray jArray = new JSONArray();
        generateJSONalumno(alumno,jArray);
        generateJSONtutoria(tutorias,jArray);
        generateJSONasigntura(asignaturas, jArray);
        //Setting xml and jarray to jsp
        request.setAttribute("Listaarrayjson", jArray);
        response.setContentType("text/xml");
        request.setAttribute("Listaxml", xml);
    }
    
    private ArrayList cleanRedundantData(ArrayList data){
        ArrayList result= new ArrayList();
        String[] txt;
        String[] txt2;
        boolean same=false;
        for(int i=0;i<data.size();i++){
            txt=(String[]) data.get(i);
            for(int k=0;k<result.size();k++){
                txt2=(String[]) result.get(k);
                if(txt[0].equalsIgnoreCase(txt2[0])){
                    same=true;
                }       
            }
            if(same==false){
                result.add(txt);
            }
        }
        return result;
    }
    
    private String getXMLdata(Alumnos al) throws IOException, JAXBException{
         return JABX.objectToStringXml(al);
    }
    private JSONObject generateJSONalumno(ArrayList alumno,JSONArray jArray){
        JSONObject obj = new JSONObject();
        String[] al=(String[]) alumno.get(0);
        obj.put("Nombre", al[0]);
        jArray.add(obj);
        return obj;
    }
    
    private void generateJSONtutoria(ArrayList tutoria,JSONArray jArray){
        String[] al;
        for(int i=0;i<tutoria.size();i++){
            JSONObject obj = new JSONObject();
            al=(String[]) tutoria.get(i);
            for(int k=0;k<al.length;k++){
                obj.put("Tutoria", al[k]);
                jArray.add(obj);
                obj = new JSONObject();
            }
        }
    }
    
    private void generateJSONasigntura(ArrayList asignatura,JSONArray jArray){
        String[] al;
        for(int i=0;i<asignatura.size();i++){
            JSONObject obj = new JSONObject();
            al=(String[]) asignatura.get(i);
            for(int k=0;k<al.length;k++){
                obj.put("Asignatura", al[k]);
                jArray.add(obj);
                obj = new JSONObject();
            }
        }
    }
}
