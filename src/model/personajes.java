package model;

import java.io.Serializable;

public class personajes implements Serializable {
    String nombrePersoanjes;

    private final static long serialVersionUID = 16789L;

    public personajes() {
    }

    public void mostrarDatos(){
        System.out.println("Nombre de persoanje = " + nombrePersoanjes);
    }

    public personajes(String nombrePersoanjes) {
        this.nombrePersoanjes = nombrePersoanjes;
    }

    public String getNombrePersoanjes() {
        return nombrePersoanjes;
    }

    public void setNombrePersoanjes(String nombrePersoanjes) {
        this.nombrePersoanjes = nombrePersoanjes;
    }
}
