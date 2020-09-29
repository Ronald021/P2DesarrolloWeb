
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

        

public class Producto  {
    Conexion cn;
   private int idProducto;
   private String producto;
   private int idMarca;
   private String descripcion;
   private float precio_costo;
   private float precio_venta;
   private int existencia;

   public Producto(){}
   public Producto(int idProducto,
                   String producto,
                   int idMarca,
                   String descripcion,
                   float precio_costo,
                   float precio_venta,
                   int existencia ){
       this.idProducto = idProducto;
       this.producto = producto;
       this. idMarca = idMarca;
       this.descripcion = descripcion;
       this.precio_costo = precio_costo;
       this.precio_venta = precio_venta;
       this .existencia = existencia;
   }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public DefaultTableModel leer(){
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "SELECT e.idProducto as id,e.producto,e.descripcion,e.precio_costo,e.precio_venta,e.existencia,p.marca,e.idmarca FROM Productos as e inner join Marcas as p on e.idmarca = p.idMarca;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","producto","Descripcion","PrecioCosto","PrecioVenta","Existencia","marca","id_marca"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[9];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("producto");
          datos[2] = consulta.getString("descripcion");
          datos[3] = consulta.getString("precio_costo");
          datos[4] = consulta.getString("precio_venta");
          datos[5] = consulta.getString("existencia");
          datos[6] = consulta.getString("marca");
          datos[7] = consulta.getString("idmarca");
          tabla.addRow(datos);
      
      }
      
     cn.cerrar_conexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }
    public int agregar(){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "insert into Productos(idProducto,producto,idmarca,descripcion,precio_costo,precio_venta,existencia) values(?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getIdProducto());
            parametro.setString(2,getProducto());
            parametro.setInt(3,getIdMarca());
            parametro.setString(4,getDescripcion());
            parametro.setFloat(5,getPrecio_costo());
            parametro.setFloat(6,getPrecio_venta());
            parametro.setInt(7, getExistencia());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    
    public int modificar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "update Productos set idProducto = ?,producto= ?,idmarca= ?,descripcion= ?,precio_costo= ?,precio_venta= ?,existencia= ? where idProducto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getIdProducto());
            parametro.setString(2,getProducto());
            parametro.setInt(3,getIdMarca());
            parametro.setString(4,getDescripcion());
            parametro.setFloat(5,getPrecio_costo());
            parametro.setFloat(6,getPrecio_venta());
            parametro.setInt(7, getExistencia());
            parametro.setInt(8, getIdProducto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
    public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "delete from Productos  where idProducto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdProducto());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
    }
   
}
