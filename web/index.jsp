<%-- 
    Document   : index
    Created on : 11/08/2020, 15:53:07
    Author     : paiz2
--%>

<%@page import="modelo.Marca" %>
<%@page import="modelo.Producto" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@ page import="modelo.Marca" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        
    </head>
    <body>
        <h1>Formulario Empleados</h1>
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_empleado" onclick="limpiar()">Nuevo</button>
        
    <div class="container">
          <div class="modal fade" id="modal_empleado" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
            <form action="sr_empleados" method="post" class="form-group">
               <label for="lbl_id" ><b>ID</b></label>
                <input type="text" name="txt_id" id="txt_id" class="form-control" value="0"  readonly> 
                <label for="lbl_producto" ><b>Producto</b></label>
                <input type="text" name="txt_prod" id="txt_prod" class="form-control" placeholder="Ejemplo: Laptop" required>
                <label for="lbl_descripcion" ><b>Descripcion</b></label>
                <input type="text" name="txt_des" id="txt_des" class="form-control" placeholder="Ejemplo: Color,tamaño..." required>
                <label for="lbl_pcosto" ><b>Precio Costo</b></label>
                <input type="number"  name="txt_pc" id="txt_pc" class="form-control" placeholder="Ejemplo: 1000" required>
                <label for="lbl_pventa" ><b>Precio Venta</b></label>
                <input type="number" name="txt_pv" id="txt_pv" class="form-control" placeholder="Ejemplo: 1200" required>
                <label for="lbl_pexistencia" ><b>Existencia</b></label>
                <input type="number" name="txt_ex" id="txt_ex" class="form-control" placeholder="Ejemplo: 15" required>
                <label for="lbl_pmarca" ><b>Marca</b></label>
                <select name="drop_puesto" id="drop_puesto" class="form-control">
                    <% 
                        Marca marca = new Marca();
                        HashMap<String,String> drop = marca.drop_marca();
                         for (String i:drop.keySet()){
                             out.println("<option value='" + i + "'>" + drop.get(i) + "</option>");
                         }
                         
                    
                    %>
                </select>
                <br>
                <button name="btn_agregar" id="btn_agregar"  value="agregar" class="btn btn-primary btn-lg">Agregar</button>
                <button name="btn_modificar" id="btn_modificar"  value="modificar" class="btn btn-success btn-lg">Modificar</button>
                <button name="btn_eliminar" id="btn_modificar"  value="eliminar" class="btn btn-danger btn-lg" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false" >Eliminar</button>
                <button type="button" class="btn btn-warning btn-lg" data-dismiss="modal">Cerrar</button>
            </form>
        </div>
      </div>
      
    </div>
  </div>
        
        
           
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Producto</th>
        <th>Descripcion</th>
        <th>Precio Costo</th>
        <th>Precio Venta</th>
        <th>Existencia</th>
        <th>Puesto</th>
      </tr>
    </thead>
    <tbody id="tbl_empleados">
        <%
            Producto empleado = new Producto();
        DefaultTableModel tabla = new DefaultTableModel();
        tabla = empleado.leer();
        for (int t=0;t<tabla.getRowCount();t++){
            out.println("<tr data-id=" + tabla.getValueAt(t,0) + " data-id_p=" + tabla.getValueAt(t,7) + ">");
            out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
            out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
            out.println("</tr>");
        
        }
        %>
      
    </tbody>
  </table>
  </div>
      

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
<script type="text/javascript">
    function limpiar(){
        $("#txt_id").val(0);
       $("#txt_prod").val('');
       $("#txt_des").val('');
       $("#txt_pc").val('');
       $("#txt_pv").val('');
       $("#txt_ex").val('');
       $("#drop_puesto").val(1);
    }
    
    $('#tbl_empleados').on('click','tr td',function(evt){
       var target,id,producto,descripcion,pc,pv,ex,id_p,a;
       target = $(event.target);
       id = target.parent().data('id');
        id_p = target.parent().data('id_p');
        producto = target.parent("tr").find("td").eq(0).html();
        descripcion= target.parent("tr").find("td").eq(1).html();
        pc = target.parent("tr").find("td").eq(2).html();
        pv = target.parent("tr").find("td").eq(3).html();
        ex = target.parent("tr").find("td").eq(4).html();
       $("#txt_id").val(id);
       $("#txt_prod").val(producto);
       $("#txt_des").val(descripcion);
       $("#txt_pc").val(pc);
       $("#txt_pv").val(pv);
        $("#txt_ex").val(ex);
        $("#drop_puesto").val(id_p);
       $("#modal_empleado").modal('show');
    });
    
</script>
    </body>
</html>
