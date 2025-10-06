
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

public class DAOCliente {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
    
    public List listaClientes(){
        List<CCliente> clientes = new ArrayList();
        String sql = "SELECT * FROM TCliente";
        try {
            rs = conexion.ejecutarConsulta(sql);
            
            while(rs.next()){
                int id = rs.getInt("id");
                String dni = rs.getString("dni");
                String nombres = rs.getString("nombres");
                String telefono = rs.getString("telefono");
                String direccion = rs.getString("direccion");
                String email = rs.getString("email");
                
                clientes.add(new CCliente(id,dni,nombres,telefono,direccion,email));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexión: " +
                e.getMessage());
        }
        return clientes;
    }
    
    public boolean agregarCliente(CCliente cliente){
        String sql = "INSERT INTO TCliente (dni,nombres,telefono,direccion,email) VALUES (?,?,?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getEmail());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean modificarCliente(CCliente cliente){
       String sql = "UPDATE TCliente SET dni=?, nombres=?, telefono=?, direccion=?, email=? WHERE id=?";
       try {
           ps = conexion.prepararConsulta(sql);
           ps.setString(1, cliente.getDni());
           ps.setString(2, cliente.getNombres());
           ps.setString(3, cliente.getTelefono());
           ps.setString(4, cliente.getDireccion());
           ps.setString(5, cliente.getEmail());
           ps.setInt(6, cliente.getId());
           ps.execute();
           return true;
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
       }
    }
    
    public boolean eliminarCliente(int codigo) {
        String sql = "DELETE FROM TCliente WHERE id = ?";
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
