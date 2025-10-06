
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

public class DAOMarca {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
    
    public List listaMarcas(){
        List<CMarca> marcas = new ArrayList();
        String sql = "SELECT * FROM TMarca";
        try {
            rs = conexion.ejecutarConsulta(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                int año = rs.getInt("año");
                int kilometraje = rs.getInt("kilometraje");
                
                marcas.add(new CMarca(id,marca,modelo,año,kilometraje));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " +
                e.getMessage());
        }
        return marcas;
    }
    
    public boolean agregarMarca(CMarca marca){
        String sql = "INSERT INTO TMarca (marca,modelo,año,kilometraje) VALUES (?,?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, marca.getMarca());
            ps.setString(2, marca.getModelo());
            ps.setInt(3, marca.getAño());
            ps.setInt(4, marca.getKilometraje());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean modificarMarca(CMarca marca){
       String sql = "UPDATE TMarca SET marca=?, modelo=?, año=?, kilometraje=? WHERE id=?";
       try {
           ps = conexion.prepararConsulta(sql);
           ps.setString(1, marca.getMarca());
           ps.setString(2, marca.getModelo());
           ps.setInt(3, marca.getAño());
           ps.setInt(4, marca.getKilometraje());
           ps.setInt(5, marca.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
    }
    
    public boolean eliminarMarca(int codigo) {
        String sql = "DELETE FROM TMarca WHERE id = ?";
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

}
