
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DAOVentaDetalle {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
        
    public List listaVentasDetalle(){
        List<CVentaDetalle> ventasDetalle = new ArrayList();
        String sql = """
            SELECT V.*,
                   P.nombre AS nombreServicio, 
                   P.precio, 
                   P.stock, 
                   P.activo,
                   T.fecha,
                   T.total,
                   M.marca,
                   M.modelo,
                   M.año
            FROM TVentaDetalle V
            INNER JOIN TServicio P ON V.id_servicio = P.id
            INNER JOIN TMarca M ON P.id_marca = M.id
            INNER JOIN TVenta T ON V.id_venta = T.id
        """;
        try {
            rs = conexion.ejecutarConsulta(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                int id_venta = rs.getInt("id_venta");
                int id_servicio = rs.getInt("id_servicio");
                int cantidad = rs.getInt("cantidad");
                double precio_unit = rs.getDouble("precio_unit");
                double subtotal = rs.getDouble("subtotal");
                String nombreServicio = rs.getString("nombreServicio");
                Double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                boolean activo = rs.getBoolean("activo");
                Date fecha = rs.getDate("fecha");
                Double total = rs.getDouble("total");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int año = rs.getInt("año");
                ventasDetalle.add(new CVentaDetalle(id,
                        new CVenta(id_venta,fecha,new CCliente(),total),
                        new CServicio(id_servicio,nombreServicio,new CMarca(0,marca,modelo,año,0),precio, stock, activo),
                        cantidad,precio_unit,subtotal));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexi n: " +
                e.getMessage());
        }
        return ventasDetalle;
    }

    public boolean agregarVentaDetalle(CVentaDetalle ventaDetalle){
        String sql = "INSERT INTO TVentaDetalle (id_venta, id_servicio, cantidad, precio_unit, subtotal) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setInt(1, ventaDetalle.getVenta().getId());
            ps.setInt(2, ventaDetalle.getServicio().getId());
            ps.setInt(3, ventaDetalle.getCantidad());
            ps.setDouble(4, ventaDetalle.getPrecio_unit());
            ps.setDouble(5, ventaDetalle.getSubtotal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
        
    public boolean modificarVentaDetalle(CVentaDetalle ventaDetalle){
       String sql = "UPDATE TVentaDetalle "
               + "SET id_venta=?, id_servicio=?, cantidad=?, precio_unit=?, subtotal=? "
               + "WHERE Id=?";
       try {
           ps = conexion.prepararConsulta(sql);
           ps.setInt(1, ventaDetalle.getVenta().getId());
           ps.setInt(2, ventaDetalle.getServicio().getId());
           ps.setInt(3, ventaDetalle.getCantidad());
           ps.setDouble(4, ventaDetalle.getPrecio_unit());
           ps.setDouble(5, ventaDetalle.getSubtotal());
           ps.setDouble(6, ventaDetalle.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
    }
    
    public boolean eliminarVentaDetalle(int codigo){
        String sql = "DELETE FROM TVentaDetalle WHERE Id = ?";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setInt(1, codigo);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public CVentaDetalle obtenerVentaDetalle(int id) {
        String sql = "SELECT * FROM TVentaDetalle WHERE id = ?";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new CVentaDetalle(
                    rs.getInt("id"),
                    new CVenta(),
                    new CServicio(),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_unit"),
                    rs.getDouble("subtotal")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener servicio: " + e.getMessage());
        }
        return null;
    }

}
