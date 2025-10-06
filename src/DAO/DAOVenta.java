
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

public class DAOVenta {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
    
    public List listaVentas(){
        List<CVenta> ventas = new ArrayList();
        String sql = "SELECT P.*, dni, nombres, telefono, direccion, email " +
                "FROM TVenta P INNER JOIN TCliente M ON P.id_cliente = M.Id";
        try {
            rs = conexion.ejecutarConsulta(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String fecha = rs.getString("fecha");
                int id_cliente = rs.getInt("id_cliente");
                Double total = rs.getDouble("total");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                ventas.add(new CVenta(id,fecha,
                        new CCliente(id_cliente,dni,nombres,telefono,direccion, email),
                        total
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " +
                e.getMessage());
        }
        return ventas;
    }
    
    //
    public boolean agregarVenta(CVenta venta){
        String sql = "INSERT INTO TVenta (fecha, total, id_marca) "
                + "VALUES (?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, venta.getFecha());
            ps.setDouble(2, venta.getTotal());
            ps.setInt(3, venta.getCliente().getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    //
    public boolean modificarVenta(CVenta venta){
       String sql = "UPDATE TVenta "
               + "SET fecha=?, total=?, id_cliente=? "
               + "WHERE Id=?";
       try {
           ps = conexion.prepararConsulta(sql);
           ps.setString(1, venta.getFecha());
           ps.setDouble(2, venta.getTotal());
           ps.setInt(3, venta.getCliente().getId());
           ps.setInt(5, venta.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }
    
    //
    public boolean eliminarServicio(int id) {
        String sql = "DELETE FROM TVenta WHERE id = ?";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
