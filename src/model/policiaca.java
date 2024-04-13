package model;

import java.io.Serializable;

public final class policiaca extends libros implements Serializable {
    private String trama;
    private personajes[] personajes;

    private final static long serialVersionUID = 13456L;
    public policiaca(String autor, String ISBN, int numeroPaginas) {
        super(autor, ISBN, numeroPaginas);
    }

    public policiaca(String autor, String ISBN, int numeroPaginas, String trama, model.personajes[] personajes) {
        super(autor, ISBN, numeroPaginas);
        this.trama = trama;
        this.personajes = personajes;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    @Override
    public void mostrardDatos() {
        super.mostrardDatos();
        for (personajes personajes:personajes) {
            personajes.mostrarDatos();
        }
        System.out.println("trama = " + trama);
    }
}
