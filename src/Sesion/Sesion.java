
package Sesion;

public class Sesion {
    public static String usuarioActivo = null;
    public static String rol = null;

    public static boolean estaLogueado() {
        return usuarioActivo != null;
    }

    public static void cerrarSesion() {
        usuarioActivo = null;
        rol = null;
    }
}
