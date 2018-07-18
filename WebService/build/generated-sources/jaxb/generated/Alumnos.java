//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci de la referencia de enlace (JAXB) XML v2.2.5-2 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: AM.10.22 a las 04:26:23 AM CEST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alumno">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="datos" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="tutoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="asignatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "alumno"
})
@XmlRootElement(name = "alumnos")
public class Alumnos {

    @XmlElement(required = true)
    protected Alumnos.Alumno alumno;

    /**
     * Obtiene el valor de la propiedad alumno.
     * 
     * @return
     *     possible object is
     *     {@link Alumnos.Alumno }
     *     
     */
    public Alumnos.Alumno getAlumno() {
        return alumno;
    }

    /**
     * Define el valor de la propiedad alumno.
     * 
     * @param value
     *     allowed object is
     *     {@link Alumnos.Alumno }
     *     
     */
    public void setAlumno(Alumnos.Alumno value) {
        this.alumno = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="datos" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="tutoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="asignatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "nombre",
        "datos"
    })
    public static class Alumno {

        @XmlElement(required = true)
        protected String nombre;
        protected List<Alumnos.Alumno.Datos> datos;

        /**
         * Obtiene el valor de la propiedad nombre.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNombre() {
            return nombre;
        }

        /**
         * Define el valor de la propiedad nombre.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNombre(String value) {
            this.nombre = value;
        }

        /**
         * Gets the value of the datos property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the datos property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDatos().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Alumnos.Alumno.Datos }
         * 
         * 
         */
        public List<Alumnos.Alumno.Datos> getDatos() {
            if (datos == null) {
                datos = new ArrayList<Alumnos.Alumno.Datos>();
            }
            return this.datos;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="tutoria" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="asignatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "tutoria",
            "asignatura"
        })
        public static class Datos {

            @XmlElement(required = true)
            protected String tutoria;
            @XmlElement(required = true)
            protected String asignatura;

            /**
             * Obtiene el valor de la propiedad tutoria.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTutoria() {
                return tutoria;
            }

            /**
             * Define el valor de la propiedad tutoria.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTutoria(String value) {
                this.tutoria = value;
            }

            /**
             * Obtiene el valor de la propiedad asignatura.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAsignatura() {
                return asignatura;
            }

            /**
             * Define el valor de la propiedad asignatura.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAsignatura(String value) {
                this.asignatura = value;
            }

        }

    }

}
