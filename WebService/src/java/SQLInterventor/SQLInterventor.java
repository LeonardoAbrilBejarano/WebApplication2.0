/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLInterventor;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Leonardo
 */
public class SQLInterventor {

    //Datos necesatios para la entrada de la base de datos
    public String bd = "videosadultos";
    public String login = "root";
    public String password = "";
    public String url = "jdbc:mysql://localhost/" + bd;
    public String semiurl = "jdbc:mysql://localhost/";

    public SQLInterventor() {
        setLogin("root");
        setBd("alumno");
    }

    //----------------------------------------------
    //Indica el nombre,etc de la base de datos
    public void setBd(String bd) {
        this.bd = bd;
        setAutoUrl(bd);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private void setAutoUrl(String bd) {
        this.url = this.semiurl + bd;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //----------------------------------------------
    public void querySQL(String query) throws SQLException {
        Connection con = conectar();
        Statement stmt = con.createStatement();
        int rs = stmt.executeUpdate(query);
    }

    public void exQueryRB(Connection c, String sql1, String sql2) {
        try {
            c.setAutoCommit(false);
            Statement st = c.createStatement();
            st.executeUpdate(sql1);
            st.executeUpdate(sql2);
            c.commit();
            System.out.println("ExecuteQuery ejecutado.");
        } catch (SQLException e) {
            try {
                c.rollback();
                System.out.println("Rollback ejecutado.");
            } catch (SQLException e1) {
                e1.printStackTrace();
                System.out.println("Error: Rollback no ejecutado");
            }
        }
    }

    public Connection conectar() {
        Connection link = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            link = DriverManager.getConnection(this.url, this.login, this.password);
        } catch (Exception e) {
            System.out.println("Peta al conectar");
            JOptionPane.showMessageDialog(null, e);
        }
        return link;
    }

    public ArrayList mostrarContenido(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        ArrayList contenido = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(vSQL);
            ResultSetMetaData rsmd = rs.getMetaData();

            contenido = new ArrayList();
            String[] objeto = new String[rsmd.getColumnCount()];

            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    objeto[i] = rs.getString(rsmd.getColumnName(i + 1));
                    //Metadata no funciona al igual que java, si es un tamaño de 4 en metadata es del 1 al 4
                    //en cambio en java es del 0 al 3
                }
                contenido.add(objeto);
                objeto = new String[rsmd.getColumnCount()];

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Peta al tratar");
        }
        cn.close();
        return contenido;
    }
    
        public ArrayList mostrarContenidoAlumnos(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        ArrayList contenido = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(vSQL);
            ResultSetMetaData rsmd = rs.getMetaData();

            contenido = new ArrayList();
            String[] objeto = new String[1];

            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    objeto[i] = rs.getString("nom");
                    //Metadata no funciona al igual que java, si es un tamaño de 4 en metadata es del 1 al 4
                    //en cambio en java es del 0 al 3
                }
                contenido.add(objeto);
                objeto = new String[rsmd.getColumnCount()];

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Peta al tratar");
        }
        cn.close();
        return contenido;
    }
    
    public ResultSet mostrarContenidoResultSet(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        ResultSet rs=null;
        try {
            Statement st = cn.createStatement();
            rs = st.executeQuery(vSQL);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Peta al tratar");
        }
        cn.close();
        return rs;
    }

    public String[][] mostrarContenidoString(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        String[][] objeto = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(vSQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            int max = obtenerNumeroDatosSql(rs);
            System.out.println(max);
            objeto = new String[max][rsmd.getColumnCount()];
            int s = 0;
            int contadorrow = 1;
            while (rs.next()) {
                for (int i = 0; i < rsmd.getColumnCount(); i++) {
                    objeto[s][i] = rs.getString(rsmd.getColumnName(i + 1));
                    //Metadata no funciona al igual que java, si es un tamaño de 4 en metadata es del 1 al 4
                    //en cambio en java es del 0 al 3
                }
                s++;
                contadorrow++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Peta al tratar");
        }
        cn.close();
        return objeto;
    }

    public int obtenerNumeroDatosSql(ResultSet rs) {
        int size = 0;
        try {
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
        } catch (Exception ex) {
            return 0;
        }
        return size;
    }

    public String[] mostrarMetaContenido(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        String[] objeto = null;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(vSQL);
            ResultSetMetaData rsmd = rs.getMetaData();
            objeto = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                objeto[i] = rsmd.getColumnName(i + 1);
                //Metadata no funciona al igual que java, si es un tamaño de 4 en metadata es del 1 al 4
                //en cambio en java es del 0 al 3
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Peta al tratar");
        }
        cn.close();
        return objeto;

    }

    public void actualizarDatosDeUnaTablaGenerada(String consulta, ArrayList contenido) throws SQLException {
        Connection cn = conectar();
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(consulta);
        ResultSetMetaData rsmd = rs.getMetaData();
        DatabaseMetaData meta = cn.getMetaData();

        String pk = rsmd.getColumnName(1);
        String table = rsmd.getTableName(1);

        String vSQL;
        String[] datos;

        System.out.println(table);
        for (int i = 0; i < contenido.size(); i++) {
            datos = (String[]) contenido.get(i);
            //UPDATE `users` SET `User_Mail` = 'luisantonio834@gmail.com' WHERE `users`.`User_Id` = 4;
            for (int k = 0; k < datos.length; k++) {
                vSQL = "Update " + table + " set " + rsmd.getColumnName(k + 1) + " = '" + datos[k] + "' where " + pk + " = " + datos[0];
                this.insertarContenidoATabla(vSQL);
            }
        }
        cn.close();
    }

    public void insertarContenido(String consulta, ArrayList contenido) throws SQLException {
        //vSQL = "INSERT INTO usuaris (nif, nom, llinatge1, llinatge2) VALUES (?, ?, ?, ?)";
        String vSQL = consulta;
        Connection cn = conectar();
        String[] objeto = null;
        try {
            PreparedStatement pst = cn.prepareStatement(vSQL);
            for (int i = 0; i < contenido.size(); i++) {
                objeto = (String[]) contenido.get(i);
                for (int k = 0; k < objeto.length; k++) {
                    pst.setString(k + 1, objeto[k]);
                }
                int n = pst.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, (i + 1) + "Inserció satisfactòria");
                }

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        cn.close();
    }

    public void insertarContenido(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        try {
            PreparedStatement pst = cn.prepareStatement(vSQL);
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Inserció satisfactòria");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        cn.close();
    }

    public void insertarContenidoATabla(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        try {
            PreparedStatement pst = cn.prepareStatement(vSQL);
            int n = pst.executeUpdate();

            if (n > 0) {
                //JOptionPane.showMessageDialog(null, "Inserció satisfactòria");
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, ex);
        }
        cn.close();
    }

    public void borrarContenido(String consulta) throws SQLException {
        String vSQL = consulta;
        Connection cn = conectar();
        try {
            PreparedStatement pst = cn.prepareStatement(vSQL);
            int n = pst.executeUpdate();

            if (n > 0) {
                JOptionPane.showMessageDialog(null, "Eliminació satisfactòria");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        cn.close();
    }
    //------------------------------------------------------------------------------------------

    public void csvComunicator(ArrayList objetos) throws SQLException {
        //UPDATE `users` SET `User_Date` = '2016-05-04' WHERE `users`.`User_Id` = 27;
        Connection cn = conectar();
        String vSQL = "";
        //Guardaremos las columnas de la tabla
        String[] Saux;
        //Guardaremos los datos a la tabla
        ArrayList aux = new ArrayList();
        //Para indicar a que clase pertenece la tabla
        boolean iniciado = false;
        //Variable que sirve para indicar al usuario en que clase se esta situando
        int clase = 1;
        for (int i = 0; i < objetos.size(); i++) {
            if (iniciado == false) {
                //Obtenemos la tabla
                String table = JOptionPane.showInputDialog("Indique a que tabla pertenece la " + clase + " clase de objetos");
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * from " + table);
                ResultSetMetaData rsmd = rs.getMetaData();
                //Obtenemos las columnas
                String[] columnas = new String[rsmd.getColumnCount()];
                for (int k = 0; k < rsmd.getColumnCount(); k++) {
                    //Lo metemos en un array de strings
                    columnas[k] = rsmd.getColumnName(k + 1);
                }
                //Generamos el insert con la tabla y las columnas
                vSQL = generarInsert(table, columnas);
                iniciado = true;
            }
            //Cojemos los datos del array list y lo volcamos al String [] aux
            Saux = (String[]) objetos.get(i);
            /*
            El funcionamiento de las condicionales es la siguiente: mientras no encuentre el string de parada de fichero o el ultimo dato del 
            arraylist de objetos, seguira añadiendo objetos al arraylist auxiliar. En cuanto entre en la condicional de proceso o de ultimo dato,
            este mandará los datos al metodo insertarContenido y se volverá a crear un nuevo auxiliar.
             */
            if (i == objetos.size() - 1) {
                aux.add(Saux);
                System.out.println(vSQL);
                insertarContenido(vSQL, aux);
                aux = new ArrayList();
            } else if (Saux[0].equalsIgnoreCase("Proceso001:Cambiodeclaseenarchivocsv")) {
                iniciado = false;
                clase++;
                System.out.println(vSQL);
                insertarContenido(vSQL, aux);
                aux = new ArrayList();
            } else {
                aux.add(Saux);
            }
        }
        cn.close();
    }

    public String generarInsert(String table, String[] columnas) {
        //INSERT INTO usuaris (nif, nom, llinatge1, llinatge2) VALUES (?, ?, ?, ?)
        String vSQL = "insert into " + table + " (";
        for (int i = 0; i < columnas.length; i++) {
            if (i == columnas.length - 1) {
                vSQL = vSQL + columnas[i];
            } else {
                vSQL = vSQL + columnas[i] + ",";
            }
        }
        vSQL = vSQL + ") values (";
        for (int i = 0; i < columnas.length; i++) {
            if (i == columnas.length - 1) {
                vSQL = vSQL + "?";
            } else {
                vSQL = vSQL + "?" + ",";
            }
        }
        vSQL = vSQL + ")";
        return vSQL;
    }
}
