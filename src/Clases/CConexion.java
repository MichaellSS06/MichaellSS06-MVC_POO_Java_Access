package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CConexion {
    private Connection conn;
    private String BDPath = "jdbc:ucanaccess://" +
            System.getProperty("user.dir") +
            "\\BD_Ventas_EFPOO.accdb";
    private Statement stmt;
    private PreparedStatement ps;
    private ResultSet rs;

    public CConexion() {
        try{   
            conn = DriverManager.getConnection(BDPath);
            stmt = conn.createStatement();
            System.out.println("Conexión exitosa!");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet ejecutarConsulta(String sql){
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error en consulta:\n" + e.getMessage());
        }
        return rs;
    }
    
    public PreparedStatement prepararConsulta(String sql){
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println("Error en consulta:\n" + e.getMessage());
        }
        return ps;
    }

}
