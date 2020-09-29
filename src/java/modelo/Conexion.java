
package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public Connection conexionBD;
    private final String puerto= "3306";
    private final String bd= "desarrolloWeb";

    private final String usuario = "wordpress";
    private final String contra = "Aa1234567+";
    private final String jdbc ="com.mysql.jdbc.Driver";
    private final String urlConexion = String.format("jdbc:mysql://127.0.0.1:%s/%s?",puerto, bd);
    public void abrir_conexion(){
        try{
            Class.forName(jdbc);
            conexionBD = DriverManager.getConnection(urlConexion,usuario,contra);
            System.out.println("Conexion Exitosa");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public void cerrar_conexion(){
        try{
            conexionBD.close();
        }catch(SQLException ex){
            System.out.println("Error: " + ex.getMessage());
        }
    
    }
}
