package model;

import java.io.Serializable;

public final class terror extends libros implements Serializable {

    private int calificacion;

    private final static long serialVersionUID = 15678L;
    public terror(String autor, String ISBN, int numeroPaginas, int calificacion) {
        super(autor, ISBN, numeroPaginas);
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public void mostrardDatos() {
        super.mostrardDatos();
        System.out.println("calificacion = " + calificacion);
    }


}
