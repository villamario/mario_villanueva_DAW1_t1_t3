package controller;

import model.comedia;
import model.libros;
import model.policiaca;
import model.terror;
import util.CatalogoNoInicializadoException;
import util.NoHayHuecoException;
import util.NoSeEncuentraException;

import java.io.*;
import java.util.ArrayList;

public class biblioteca {
    private String nombre, director;
    private static catalogo catalogo;

    public biblioteca() {
    }

    public biblioteca(String nombre, String director, biblioteca.catalogo catalogo, ArrayList<model.libros> listaLibros) {
        this.nombre = nombre;
        this.director = director;
        this.catalogo = catalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public biblioteca.catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(biblioteca.catalogo catalogo) {
        this.catalogo = catalogo;
    }


    public class catalogo {

        private ArrayList<libros> listaLibros;

        public catalogo() {
            listaLibros = new ArrayList<>();
        }

        int capacidad;


        public int getCapacidad() {
            return capacidad;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        public void agregarLibro(libros libros) {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            if ((capacidad > listaLibros.size()) && (estaLibro(libros.getISBN())==null)) {
                listaLibros.add(libros);
                System.out.println("agregado ✅📖");
                System.out.println("el tamaño ahora es de "+ listaLibros.size());
            } else {
                if (capacidad <= listaLibros.size()){
                    throw new NoHayHuecoException ("No está el libro 🔍❌");
                } else {
                    System.out.println("Está duplicado 🔁❌");
                }
            }
        }

        public libros estaLibro(String isbn){
            for (libros item:listaLibros) {
                if (isbn.equalsIgnoreCase(item.getISBN())){
                    return item;
                }
            } return null;
        }

        public void listarLibros() {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            for (libros item : listaLibros) {
                System.out.println("el tamaño ahora es de "+ listaLibros.size());
                System.out.println();
                item.mostrardDatos();
                System.out.println();
            }
        }

        public void buscarLibros(String isbn) {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            boolean encontrado = false;
            for (libros item : listaLibros) {
                if (item.getISBN().equalsIgnoreCase(isbn)) {
                    System.out.println("Encontrado 🔍📚");
                    item.mostrardDatos();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                throw new NoSeEncuentraException ("No está el libro 🔍❌");
            }
        }

        public void eliminarLibro(String isbn) {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            for (libros item : listaLibros) {
                if (item.getISBN().equalsIgnoreCase(isbn)) {
                    listaLibros.remove(item);
                    System.out.println("Eliminado");
                }else{
                    System.out.println("No está el libro 🔍❌");
                }
                    break;

            }

        }

        public  void escribirLibro() throws IOException {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            File file = new File("src/resources/libros.obj");
            ObjectOutputStream ObjectOutputStream = new ObjectOutputStream(new FileOutputStream(file));

            ObjectOutputStream.writeObject(listaLibros);

            ObjectOutputStream.close();

        }


        public void leerLibro() throws IOException, ClassNotFoundException, ClassCastException {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            File file = new File("src/resources/libros.obj");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            ArrayList<libros> listaLibros = (ArrayList<libros>) objectInputStream.readObject();
            objectInputStream.close();
            for (libros libro : listaLibros) {
                libro.mostrardDatos();
                System.out.println(); // Agregar una línea en blanco entre cada libro
            }
        }

    }
}