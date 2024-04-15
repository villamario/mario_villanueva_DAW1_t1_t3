package controller;

import model.libros;
import util.CatalogoNoInicializadoException;
import util.NoHayHuecoException;
import util.NoSeEncuentraException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class biblioteca {
    private String nombre, director;
    private catalogo catalogo;

    private Deposito deposito;



    public biblioteca() {
        deposito = new Deposito();
    }

    public biblioteca(String nombre, String director, biblioteca.catalogo catalogo, ArrayList<model.libros> listaLibros) {
        this.nombre = nombre;
        this.director = director;
        this.catalogo = catalogo;
    }
    public Deposito getDeposito() {
        return deposito;
    }
    public static class Deposito {
        private List<libros> librosDepositados;

        public Deposito() {
            librosDepositados = new ArrayList<>();
        }

        public void agregarLibro(libros libro) {
            for (libros item : librosDepositados) {
                if (item.getISBN().equals(libro.getISBN())) {
                    System.out.println("ISBN duplicado: " + libro.getISBN());
                    return; // Salir del método si se encuentra un duplicado
                }
            }
            // Si no se encontró ningún duplicado, agregar el libro al depósito
            librosDepositados.add(libro);
            System.out.println("Libro agregado al depósito.");
        }

        public void mostrarLibrosDepositados() {
            System.out.println("Libros en el depósito:");
            for (libros libro : librosDepositados) {
                System.out.println(libro);
            }
        }

        public List<libros> getLibrosDepositados() {
            return librosDepositados;
        }

        public libros obtenerLibroPorISBN(String isbn) {
            for (libros libro : librosDepositados) {
                if (libro.getISBN().equalsIgnoreCase(isbn)) {
                    return libro;
                }
            }
            return null;
        }

        public void eliminarLibro(libros libro) {
            librosDepositados.remove(libro);
            System.out.println("Libro eliminado del depósito.");
        }

    }

    public void agregarLibroACatalogo(libros nuevoLibro) {
        catalogo.agregarLibro(nuevoLibro);
    }

    public void agregarLibro(libros libro) throws NoHayHuecoException {
        if (catalogo == null) {
            throw new RuntimeException("El catálogo no ha sido inicializado.");
        }
        try {
            catalogo.agregarLibro(libro);
        } catch (NoHayHuecoException e) {
            throw new NoHayHuecoException("No hay hueco en el catálogo para agregar libros.");
        }
    }

    public void listarLibrosDeposito() {
        if (deposito != null) {
            for (libros item : deposito.librosDepositados) {
                item.mostrardDatos();
                System.out.println();
            }
        } else {
            System.out.println("El depósito no tiene libros.");
        }
    }

    public void listarLibros() {
        catalogo.listarLibros();
    }

    public void buscarLibro(String isbn) {
        catalogo.buscarLibros(isbn);
    }

    public void eliminarLibro(String isbn) {
        catalogo.eliminarLibro(isbn);
    }

    public void exportarLibros() throws IOException {
        catalogo.escribirLibro();
    }

    public void importarLibros() throws IOException, ClassNotFoundException, ClassCastException {
        catalogo.leerLibro();
    }
    public void crearCatalogo() {
        this.catalogo = new catalogo(); // Crear una instancia de la clase interna "catalogo"
        System.out.println("Se ha creado el catálogo.");
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


    public static class catalogo {

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
            if ((capacidad > listaLibros.size()) && (estaLibro(libros.getISBN()) == null)) {
                listaLibros.add(libros);
                System.out.println("agregado ✅📖");
                System.out.println("el tamaño ahora es de " + listaLibros.size());
            } else {
                if (capacidad <= listaLibros.size()) {
                    throw new NoHayHuecoException("No hay hueco 📚❌");
                } else {
                    System.out.println("Está duplicado 🔁❌");
                }
            }
        }

        public libros estaLibro(String isbn) {
            for (libros item : listaLibros) {
                if (isbn.equalsIgnoreCase(item.getISBN())) {
                    return item;
                }
            }
            return null;
        }

        public void listarLibros() {
            if (listaLibros == null) {
                throw new CatalogoNoInicializadoException("El catálogo no ha sido inicializado.");
            }
            System.out.println("Tamaño de la lista de libros: " + listaLibros.size());
            if (listaLibros.isEmpty()) {
                System.out.println("La lista de libros está vacía.");
            }
            for (libros item : listaLibros) {
                item.mostrardDatos();
                System.out.println();
            }
            System.out.println("El tamaño de la lista de libros es: " + listaLibros.size());
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
                throw new NoSeEncuentraException("No está el libro 🔍❌");
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
                } else {
                    System.out.println("No está el libro 🔍❌");
                }
                break;

            }

        }

        public void escribirLibro() throws IOException {
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
                System.out.println();
            }
        }

    }
}