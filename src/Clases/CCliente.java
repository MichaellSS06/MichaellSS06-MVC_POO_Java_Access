
package Clases;


public class CCliente {
    private int id;
    private String dni;
    private String nombres;
    private String telefono;
    private String direccion;
    private String email;

    public CCliente(int id, String dni, String nombres, String telefono, String direccion, String email) {
        this.id = id;
        this.dni = dni;
        this.nombres = nombres;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombres() {
        return nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
