
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

public class DAOServicio {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
    
    public List listaServicios(){
        List<CServicio> servicios = new ArrayList();
        String sql = "SELECT P.*, marca, modelo, año, kilometraje " +
                "FROM TServicio P INNER JOIN TMarca M ON P.id_marca = M.Id";
        try {
            rs = conexion.ejecutarConsulta(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int id_marca = rs.getInt("id_marca");
                int stock = rs.getInt("stock");
                boolean activo = rs.getBoolean("activo");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int año = rs.getInt("año");
                int kilometraje = rs.getInt("kilometraje");
                servicios.add(new CServicio(id,nombre,
                        new CMarca(id_marca,marca,modelo,año,kilometraje),
                        precio,stock, activo
                        ));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " +
                e.getMessage());
        }
        return servicios;
    }
    
    //
    public boolean agregarServicio(CServicio servicio){
        String sql = "INSERT INTO TServicio (nombre, precio, stock, activo, id_marca) "
                + "VALUES (?,?,?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, servicio.getNombre());
            ps.setDouble(2, servicio.getPrecio());
            ps.setInt(3, servicio.getStock());
            ps.setBoolean(4, servicio.isActivo());
            ps.setInt(5, servicio.getMarca().getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    //
    public boolean modificarServicio(CServicio servicio){
       String sql = "UPDATE TServicio "
               + "SET nombre=?, precio=?, stock=?, activo=?, id_marca=? "
               + "WHERE Id=?";
       try {
           ps = conexion.prepararConsulta(sql);
           ps.setString(1, servicio.getNombre());
           ps.setDouble(2, servicio.getPrecio());
           ps.setInt(3, servicio.getStock());
           ps.setBoolean(4, servicio.isActivo());
           ps.setInt(5, servicio.getMarca().getId());
           ps.setInt(6, servicio.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
   }
    
    //
    public boolean eliminarServicio(int id) {
        String sql = "DELETE FROM TServicio WHERE id = ?";
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
