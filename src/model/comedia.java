package model;

import java.io.Serializable;

public final class comedia extends libros implements Serializable {
    private String tipoDeHumor;

    private final static long serialVersionUID = 145678L;

    public comedia(String autor, String ISBN, int numeroPaginas, String tipoDeHumor) {
        super(autor, ISBN, numeroPaginas);
        this.tipoDeHumor = tipoDeHumor;
    }

    public String getTipoDeHumor() {
        return tipoDeHumor;
    }

    public void setTipoDeHumor(String tipoDeHumor) {
        this.tipoDeHumor = tipoDeHumor;
    }

    @Override
    public void mostrardDatos() {
        super.mostrardDatos();
        System.out.println("tipoDeHumor = " + tipoDeHumor);
    }

}
