
package DAO;

import Clases.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    CConexion conexion = new CConexion();
    ResultSet rs;
    PreparedStatement ps;
    
    public boolean agregarUsuario(CUsuario usuario){
        String sql = "INSERT INTO TUsuario (username,password,nombre,rol) VALUES (?,?,?,?)";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getRol());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public boolean logearUsuario(CUsuario usuario){
        String sql = "SELECT * FROM TUsuario WHERE username=? AND password=?";
        try {
            ps = conexion.prepararConsulta(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                // ✅ Hay coincidencia
                return true;
            } else {
                // ❌ Usuario o contraseña incorrectos
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
        }
    }
}
