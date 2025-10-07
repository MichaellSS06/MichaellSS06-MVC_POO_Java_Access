
package Clases;

public class CServicio {
    private int id;
    private String nombre;
    private CMarca marca;
    private Double precio;
    private int stock;
    private boolean activo;

    public CServicio() {
    }

    
    public CServicio(int id, String nombre, CMarca marca, Double precio, int stock, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public CMarca getMarca() {
        return marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(CMarca marca) {
        this.marca = marca;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }    

}
