
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Marca {
    private int idMarca;
    private String marca;
    Conexion cn;
    public Marca(){}
    public Marca(int id_puesto, String puesto) {
        this.idMarca = id_puesto;
        this.marca = puesto;
    }

    public int getId_puesto() {
        return idMarca;
    }

    public void setId_puesto(int id_puesto) {
        this.idMarca = id_puesto;
    }

    public String getPuesto() {
        return marca;
    }

    public void setPuesto(String puesto) {
        this.marca = puesto;
    }

    public HashMap drop_marca(){
    HashMap<String,String> drop = new HashMap();
    try{
        String query ="Select idMarca as id,marca from Marcas";
         cn = new Conexion();
         cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
            drop.put(consulta.getString("id"),consulta.getString("marca") );
            }
         cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }
    return drop;
    }
    
}
