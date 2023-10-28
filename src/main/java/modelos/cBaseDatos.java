package modelos;

import java.sql.*;
import java.util.*;

public class cBaseDatos {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    String usuario = "root";
    String clave = "12345678";

    // Constructor de la clase
    public cBaseDatos() {
        // Puedes inicializar la conexión o realizar otras tareas aquí si es necesario.
    }

    // Método privado para establecer la conexión a la base de datos
    private Connection Conectar() {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url, usuario, clave);
            return xcon;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    // Método privado para convertir un ResultSet a un Vector de Vectores
    private Vector<Vector<String>> ResultSetToVector(ResultSet rs) throws SQLException {
        Vector<Vector<String>> vRows = new Vector<>();
        while (rs.next()) {
            Vector<String> vCol = new Vector<>();
            int nroFields = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= nroFields; i++) {
                String strTmp = rs.getString(i);
                vCol.add(strTmp);
            }
            vRows.add(vCol);
        }
        return vRows;
    }

    // Método público para obtener áreas desde la base de datos
    public Vector<Vector<String>> getAreas() {
        Vector<Vector<String>> vRet = null;
        try {
            String sql = "select codigo, nombre, abreviatura, estado from areas2 order by codigo";
            Connection xcon = this.Conectar();
            Statement stm = xcon.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            vRet = ResultSetToVector(rs);
            rs.close();
            stm.close();
            xcon.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vRet;
    }

    // Método público para generar un código automático para una tabla
    protected String generarCodigo(String tabla, String campo) throws SQLException {
        String rpta = "";
        String sql = "select count(*) from " + tabla;
        Connection xcon = this.Conectar();
        Statement st = xcon.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        int cant = Integer.parseInt(rs.getString(1));
        
        if (cant <= 0) {
            rpta = "1";
        } else {
            sql = "select max(" + campo + ") from " + tabla;
            rs = st.executeQuery(sql);
            rs.next();
            cant = Integer.parseInt(rs.getString(1)) + 1;
            rpta = String.valueOf(cant);
        }
        
        xcon.close();
        return rpta;
    }
    public void grabarNuevaArea( String[] datos ) throws SQLException {
   String xcod = this.generarCodigo("areas2","codigo");
   String xnom = datos[0];
   String xabr = datos[1];
   String xest = datos[2];
   String sql = "insert into areas2 (codigo,nombre,abreviatura,estado) values (?,?,?,?) ";
   Connection xcon = this.Conectar();
   PreparedStatement ps = xcon.prepareStatement(sql);
   ps.setString(1, xcod);
   ps.setString(2, xnom);
   ps.setString(3, xabr);
   ps.setString(4, xest);
   ps.executeUpdate();
   xcon.close();
}
    public Vector buscarArea( String pCodigo ) throws SQLException {
   String sql  = "select codigo, nombre, abreviatura, estado " +
           "from areas2 where codigo=?";
   Connection xcon = this.Conectar();
   PreparedStatement ps = xcon.prepareStatement(sql);
   ps.setString(1, pCodigo);
   ResultSet rs = ps.executeQuery();
   Vector vRet = ResultSetToVector(rs);
   rs.close();
   ps.close();
   xcon.close();
   Vector fila = (Vector)vRet.get(0);
   return fila;
}
public void modificarArea( String[] datos ) throws SQLException {
   String xcod = datos[0];
   String xnom = datos[1];
   String xabr = datos[2];
   String xest = datos[3];
   String sql = "update areas2 set nombre=?, abreviatura=?, estado=? where codigo=? ";
   Connection xcon = this.Conectar();
   PreparedStatement ps = xcon.prepareStatement(sql);
   ps.setString(1, xnom);
   ps.setString(2, xabr);
   ps.setString(3, xest);
   ps.setString(4, xcod);
   ps.executeUpdate();
   xcon.close();
}

public void eliminarAreas( String[] datos ) throws SQLException {
   boolean inicio;
   if ( datos.length <= 0 )
      return;
   String sql  = "DELETE FROM areas2 WHERE codigo in ( ";
   inicio = true;
   for( int xc = 0 ; xc < datos.length ; xc++ ) {
       if ( inicio )
         sql += "?";
       else
         sql += ",?";
       inicio = false;
   }
   sql += ")";
   Connection xcon = this.Conectar();
   PreparedStatement ps = xcon.prepareStatement(sql);
   for( int xc = 0 ; xc < datos.length ; xc++ ) 
      ps.setString(xc+1, datos[xc]);
   ps.executeUpdate();
   xcon.close();
}

public class AutenticacionUsuario {
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    private String usuario = "root";
    private String clave = "12345678";

    public AutenticacionUsuario() {
        // Puedes inicializar la conexión o realizar otras tareas aquí si es necesario.
    }

    public boolean autenticarUsuario(String username, String password) {
        try {
            Class.forName(driver);
            Connection xcon = DriverManager.getConnection(url, usuario, clave);
            
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement ps = xcon.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            boolean autenticado = rs.next(); // Si la consulta devuelve resultados, el usuario está autenticado.
            
            rs.close();
            ps.close();
            xcon.close();
            
            return autenticado;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
      }
  }    
}
