
package Clases;
import java.util.Date;

public class CVenta {
    private int id;
    private Date fecha;
    private CCliente cliente;
    private double total;

    public CVenta() {
    }

    public CVenta(int id, Date fecha, CCliente cliente, double total) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setCliente(CCliente cliente) {
        this.cliente = cliente;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
