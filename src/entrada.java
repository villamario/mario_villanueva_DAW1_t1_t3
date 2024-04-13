import controller.biblioteca;
import model.*;
import util.CatalogoNoInicializadoException;
import util.NoHayHuecoException;
import util.NoSeEncuentraException;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class entrada {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        biblioteca biblioteca = null;
        biblioteca.catalogo catalogo = null;

        int opcion = 0;
        int capacidad = 0;

        do {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Crea una biblioteca\n" +
                    "2. Crea un catálogo de libros\n" +
                    "3. Agregar libros al catálogo\n" +
                    "4. Muestra la información de todos los libros\n" +
                    "5. Buscar libros libros\n" +
                    "6. Eliminar libros\n" +
                    "7. Exporta todos los libros del catálogo a un fichero llamado libros.obj\n" +
                    "8. Leer libros.obj\n" +
                    "9. Salir");

            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    biblioteca = new biblioteca();
                    System.out.println("Pon el nombre de la biblioteca");
                    String nombreTeclado = teclado.next();
                    biblioteca.setNombre(nombreTeclado);
                    System.out.println("Pon el nombre del director");
                    String directorTeclado = teclado.next();
                    biblioteca.setDirector(directorTeclado);
                    System.out.println("Nombre de la biblioteca: " + biblioteca.getNombre() + "\nNombre del director: " + biblioteca.getDirector());
                    break;

                case 2:
                    if (biblioteca == null) {
                        System.out.println("Debes crear primero una biblioteca.");
                        break;
                    }
                    catalogo = biblioteca.new catalogo();
                    System.out.println("capacidad del catalogo");
                    capacidad = teclado.nextInt();
                    catalogo.setCapacidad(capacidad);
                    System.out.println("tu capacidad es de: " + catalogo.getCapacidad() + " 📚");
                    break;

                case 3:
                    if (catalogo != null) {
                        try {
                    System.out.println("Que tipo de libro?\n" +
                            "1.Agregar libro de terror\n" +
                            "2.Agregar libro de comedia\n" +
                            "3.Agregar libro policiaca");
                    int opcionAñadir = 0;
                    opcionAñadir = teclado.nextInt();
                    System.out.println("añade los datos:");
                    System.out.println("autor");
                    String autor = teclado.next();
                    System.out.println("ISBN");
                    String ISBN = teclado.next();
                    System.out.println("Nunero paginas");
                    int numeroPaginas = teclado.nextInt();
                    libros libros = null;
                    String isbn = ISBN;

                    switch (opcionAñadir) {
                        case 1:
                            System.out.println("Clasificacion");
                            int clasificacion = teclado.nextInt();
                            libros = new terror(autor, ISBN, numeroPaginas, clasificacion);

                            break;

                        case 2:
                            System.out.println("Tipo de humor");
                            String tipoDeHumor = teclado.next();
                            libros = new comedia(autor, ISBN, numeroPaginas, tipoDeHumor);
                            break;

                        case 3:
                            System.out.println("Trama");
                            String trama = teclado.next();
                            System.out.println("Ingrese la cantidad de personajes:");
                            int cantidadPersonajes = teclado.nextInt();

                            personajes[] personajes = new personajes[cantidadPersonajes];

                            for (int i = 0; i < cantidadPersonajes; i++) {
                                System.out.println("Ingrese el nombre del personaje " + (i + 1) + ":");
                                String nombrePersonaje = teclado.next();
                                personajes[i] = new personajes(nombrePersonaje);
                            }

                            libros = new policiaca(autor, isbn, numeroPaginas, trama, personajes);
                            break;

                    }
                    catalogo.agregarLibro(libros);
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");

                    } catch (NoHayHuecoException e) {
                    System.out.println("No hay hueco en el catalogo");
                }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }
                    break;


                case 4:
                    if (catalogo != null) {
                        try {
                    catalogo.listarLibros();
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");
                        }
                        } else {
                            throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                        }
                        break;

                case 5:
                    System.out.println("ISBN que quieres buscar");
                    String buscarISBN = teclado.next();
                    if (catalogo != null) {
                        try {
                            catalogo.buscarLibros(buscarISBN);
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");
                        } catch (NoSeEncuentraException e) {
                            System.out.println("No se encontró el libro");
                        }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }
                    break;

                case 6:

                    System.out.println("Isbn que quieres eliminar");
                    String eliminarISBN = teclado.next();
                    if (catalogo != null) {
                        try {
                            catalogo.eliminarLibro(eliminarISBN);
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");
                        }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }
                    break;

                case 7:
                    if (catalogo != null) {
                        try {
                            try {
                                catalogo.escribirLibro();
                                System.out.println("Se ha escrito el fichero");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");
                        }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }


                    break;

                case 8:
                    if (catalogo != null) {
                        try {
                            try {
                                catalogo.leerLibro();
                            } catch (IOException e) {
                                System.out.println("Fallo");
                            } catch (ClassNotFoundException e) {
                                System.out.println("Fallo no se encuentra");
                            } catch (ClassCastException e) {
                                System.out.println("Fallo de clase");
                            }
                            System.out.println("Se ha leido el fichero");
                            break;
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("No está inicializado el catálogo");
                        }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }

            }

        }while (opcion != 9) ;
    }
}

