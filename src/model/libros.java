package model;

import java.io.Serializable;

public abstract class libros implements Serializable {
    private String autor, ISBN;
    private int numeroPaginas;

    private final static long serialVersionUID = 12345L;


    public libros(String autor, String ISBN, int numeroPaginas) {
        this.autor = autor;
        this.ISBN = ISBN;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void mostrardDatos(){
        System.out.println("Autor = " + autor);
        System.out.println("ISBN = " + ISBN);
        System.out.println("Numero de paginas = " + numeroPaginas);
    }
}
