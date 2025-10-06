
package Clases;

public class CVentaDetalle {
    private int id;
    private CVenta venta;
    private CServicio servicio;
    private int cantidad;
    private double precio_unit;
    private double subtotal;

    public CVentaDetalle(int id, CVenta venta, CServicio servicio, int cantidad, double precio_unit, double subtotal) {
        this.id = id;
        this.venta = venta;
        this.servicio = servicio;
        this.cantidad = cantidad;
        this.precio_unit = precio_unit;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public CVenta getVenta() {
        return venta;
    }

    public CServicio getServicio() {
        return servicio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVenta(CVenta venta) {
        this.venta = venta;
    }

    public void setServicio(CServicio servicio) {
        this.servicio = servicio;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
