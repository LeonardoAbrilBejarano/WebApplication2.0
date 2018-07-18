/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAXB;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import generated.Alumnos;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author leillo
 */
public class JABX {

    public static Alumnos xmlToObject(File f) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //Do the job, return object
            return (Alumnos) jaxbUnmarshaller.unmarshal(f);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 
    public static Catalog xmlToObject(File f){
 try {
  JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
  Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

  //Do the job, return object
  return (Catalog)jaxbUnmarshaller.unmarshal(f);
 } catch (JAXBException e) {
  e.printStackTrace();
  return null;
 }
}
     */

    public static Alumnos generarPersonas(ArrayList p, ArrayList p2, ArrayList p3) throws DatatypeConfigurationException {
        Alumnos pers = new Alumnos();
        Alumnos.Alumno pe = new Alumnos.Alumno();
        Alumnos.Alumno.Datos dt = new Alumnos.Alumno.Datos();
        String[] datos = (String[]) p.get(0);
        pe.setNombre(datos[0]);
        for (int i = 0; i < p2.size(); i++) {
            datos = (String[]) p2.get(i);
            dt.setTutoria(datos[0]);
            System.out.print(datos[0]);
            datos = (String[]) p3.get(i);
            dt.setAsignatura(datos[0]);
            System.out.print(datos[0]);
            pe.getDatos().add(dt);
            dt = new Alumnos.Alumno.Datos();
        }
        pers.setAlumno(pe);
        return pers;
    }

    private static XMLGregorianCalendar ConvertXmlCalendar(String a) throws DatatypeConfigurationException {
        String[] data = a.split("/");
        Date d = new Date();
        d.setDate(Integer.parseInt(data[0]));
        d.setMonth(Integer.parseInt(data[1]));
        d.setYear(Integer.parseInt(data[2]));
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(d);
        XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        return date2;

    }

    public static void objectToXml(Alumnos cds, File rf) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Optional
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Do the job
            jaxbMarshaller.marshal(cds, rf);
            
            //Optional: output pretty printed
            //jaxbMarshaller.marshal(cds, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static String objectToStringXml(Alumnos cds) throws JAXBException {
            JAXBContext jaxbContext = JAXBContext.newInstance(Alumnos.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Optional
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //Do the job
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(cds, sw);
            String xmlString = sw.toString();
            
            return xmlString;
    }

}

/*
    private static void printObject(Catalog cds)
{
 String result="";
 //Read every CD from list
 List<Catalog.Cd> llistacd=(List<Catalog.Cd>) cds.getCd();
 for (int i=0; i<llistacd.size(); i++) {
  result+="\n "+"Publication date: "+llistacd.get(i).getPublicationDate();
  result+="\n "+"Title: "+llistacd.get(i).getTitle();
  result+="\n "+"Artist: "+llistacd.get(i).getArtist();
  result+="\n ";
 }
 System.out.println(result);
}
 */
