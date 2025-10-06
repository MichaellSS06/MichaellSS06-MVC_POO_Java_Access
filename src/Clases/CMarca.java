
package Clases;

public class CMarca {
    private int id;
    private String marca;
    private String modelo;
    private int año;
    private int kilometraje;

    public CMarca() {
    }

    public CMarca(int id, String marca, String modelo, int año, int kilometraje) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.kilometraje = kilometraje;
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAño() {
        return año;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

}
