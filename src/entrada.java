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

    // Clase interna para la biblioteca de terror
    public static class BibliotecaDeTerror extends biblioteca {
        // Puedes agregar métodos específicos para la biblioteca de terror si es necesario
    }

    // Clase interna para la biblioteca de comedia
    public static class BibliotecaDeComedia extends biblioteca {
        // Puedes agregar métodos específicos para la biblioteca de comedia si es necesario
    }

    // Clase interna para la biblioteca policiaca
    public static class BibliotecaPoliciaca extends biblioteca {
        // Puedes agregar métodos específicos para la biblioteca policiaca si es necesario
    }
    public static class BibliotecaGeneral extends biblioteca {
        // Puedes agregar métodos específicos para la biblioteca policiaca si es necesario
    }
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        biblioteca miBiblioteca = null;
        biblioteca.catalogo catalogo = null;

        int opcion = 0;

        do {
            System.out.println("¿Qué quieres hacer?");
            System.out.println("1. Crea una biblioteca\n" +
                    "2. Crea un catálogo de libros\n" +
                    "3. Agregar libros al depósito\n" +
                    "4. Agregar libros al catálogo\n" +
                    "5. Mostrar la información de todos los libros\n" +
                    "6. Buscar libros\n" +
                    "7. Eliminar libros\n" +
                    "8. Exportar todos los libros del catálogo a un fichero\n" +
                    "9. Leer libros desde un fichero\n" +
                    "10. Listar libros del depósito\n" +
                    "11. Salir");

            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("¿Qué tipo de biblioteca deseas crear?");
                    System.out.println("1. Biblioteca general");
                    System.out.println("2. Biblioteca de terror");
                    System.out.println("3. Biblioteca de comedia");
                    System.out.println("4. Biblioteca policiaca");
                    int opcionTipoBiblioteca = teclado.nextInt();
                    switch (opcionTipoBiblioteca) {
                        case 1:
                            miBiblioteca = new BibliotecaGeneral();
                            break;
                        case 2:
                            miBiblioteca = new BibliotecaDeTerror();
                            break;
                        case 3:
                            miBiblioteca = new BibliotecaDeComedia();
                            break;
                        case 4:
                            miBiblioteca = new BibliotecaPoliciaca();
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    if (miBiblioteca != null) {
                        System.out.println("Pon el nombre de la biblioteca");
                        String nombreTeclado = teclado.next();
                        miBiblioteca.setNombre(nombreTeclado);
                        System.out.println("Pon el nombre del director");
                        String directorTeclado = teclado.next();
                        miBiblioteca.setDirector(directorTeclado);
                        System.out.println("Nombre de la biblioteca: " + miBiblioteca.getNombre() + "\nNombre del director: " + miBiblioteca.getDirector());
                    }
                    break;

                case 2:
                    if (miBiblioteca == null) {
                        System.out.println("Debes crear primero una biblioteca.");
                        break;
                    }
                    miBiblioteca.crearCatalogo(); // Crear un nuevo catálogo
                    biblioteca.catalogo nuevoCatalogo = miBiblioteca.getCatalogo(); // Obtener el catálogo recién creado
                    System.out.println("Capacidad del catálogo:");
                    int capacidad = teclado.nextInt();
                    nuevoCatalogo.setCapacidad(capacidad);
                    System.out.println("Tu catálogo tiene una capacidad de: " + nuevoCatalogo.getCapacidad() + " libros 📚");
                    miBiblioteca.setCatalogo(nuevoCatalogo); // Asignar el nuevo catálogo a la biblioteca
                    break;

                case 3:
                    if (miBiblioteca != null) {
                        if (miBiblioteca.getCatalogo() != null) {
                            try {
                                System.out.println("¿Qué tipo de libro deseas agregar?");
                                System.out.println("1. Terror");
                                System.out.println("2. Comedia");
                                System.out.println("3. Policiaca");
                                int opcionTipoLibro = teclado.nextInt();

                                System.out.println("Ingresa los detalles del libro:");
                                System.out.println("Autor:");
                                String autor = teclado.next();
                                System.out.println("ISBN:");
                                String isbn = teclado.next();
                                System.out.println("Número de páginas:");
                                int numeroPaginas = teclado.nextInt();

                                libros nuevoLibro = null;

                                switch (opcionTipoLibro) {
                                    case 1:
                                        System.out.println("Calificación:");
                                        int calificacion = teclado.nextInt();
                                        nuevoLibro = new terror(autor, isbn, numeroPaginas, calificacion);
                                        break;
                                    case 2:
                                        System.out.println("Tipo de humor:");
                                        String tipoDeHumor = teclado.next();
                                        nuevoLibro = new comedia(autor, isbn, numeroPaginas, tipoDeHumor);
                                        break;
                                    case 3:
                                        System.out.println("Trama:");
                                        String trama = teclado.next();
                                        System.out.println("Cantidad de personajes:");
                                        int cantidadPersonajes = teclado.nextInt();
                                        personajes[] personajesArray = new personajes[cantidadPersonajes];
                                        for (int i = 0; i < cantidadPersonajes; i++) {
                                            System.out.println("Nombre del personaje " + (i + 1) + ":");
                                            String nombrePersonaje = teclado.next();
                                            personajesArray[i] = new personajes(nombrePersonaje);
                                        }
                                        nuevoLibro = new policiaca(autor, isbn, numeroPaginas, trama, personajesArray);
                                        break;
                                    default:
                                        System.out.println("Opción no válida.");
                                        break;
                                }

                                // Agregar el libro al depósito de la biblioteca
                                miBiblioteca.getDeposito().agregarLibro(nuevoLibro);

                            } catch (NoHayHuecoException e) {
                                System.out.println("No hay suficiente espacio en el depósito para agregar el libro.");
                            } catch (CatalogoNoInicializadoException e) {
                                System.out.println("El catálogo no está inicializado.");
                            }
                        } else {
                            System.out.println("El catálogo no está inicializado.");
                        }
                    } else {
                        System.out.println("Debes crear primero una biblioteca.");
                    }
                    break;

                case 4:
                    if (miBiblioteca != null && miBiblioteca.getCatalogo() != null) {
                        try {
                            System.out.println("Ingresa el ISBN del libro que deseas agregar al catálogo:");
                            String isbnToAdd = teclado.next();

                            // Obtener el libro del depósito por su ISBN
                            libros libroToAdd = miBiblioteca.getDeposito().obtenerLibroPorISBN(isbnToAdd);

                            // Verificar si se encontró el libro en el depósito
                            if (libroToAdd != null) {
                                // Si es una biblioteca general, permitir la adición de cualquier libro al catálogo
                                if (miBiblioteca instanceof BibliotecaGeneral) {
                                    miBiblioteca.agregarLibroACatalogo(libroToAdd);
                                    System.out.println("Libro agregado al catálogo desde el depósito.");
                                } else {
                                    // Si no es una biblioteca general, verificar el tipo de libro
                                    if ((miBiblioteca instanceof BibliotecaDeTerror && libroToAdd instanceof terror) ||
                                            (miBiblioteca instanceof BibliotecaDeComedia && libroToAdd instanceof comedia) ||
                                            (miBiblioteca instanceof BibliotecaPoliciaca && libroToAdd instanceof policiaca)) {
                                        miBiblioteca.agregarLibroACatalogo(libroToAdd);
                                        System.out.println("Libro agregado al catálogo desde el depósito.");
                                    } else {
                                        System.out.println("El libro con el ISBN proporcionado no coincide con el tipo de biblioteca.");
                                    }
                                }
                            } else {
                                System.out.println("El libro con el ISBN proporcionado no está en el depósito.");
                            }
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catálogo no está inicializado");
                        } catch (NoSeEncuentraException e) {
                            System.out.println("No se encuentra el libro");
                        }
                    } else {
                        throw new CatalogoNoInicializadoException("El catálogo no está inicializado");
                    }
                    break;


                case 5:
                    if (miBiblioteca.getCatalogo() != null) {
                        try {
                            // Mostrar la información del catálogo si tiene libros
                            miBiblioteca.listarLibros();
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catalogo no esta inicializado");

                        }
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;

                case 6:
                    if (miBiblioteca != null) {
                        System.out.println("ISBN que quieres buscar");
                        String buscarISBN = teclado.next();
                        try {
                            miBiblioteca.buscarLibro(buscarISBN);
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catálogo no está inicializado");
                        } catch (NoSeEncuentraException e) {
                            System.out.println("El libro no se encuentra");
                        }
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;

                case 7:
                    if (miBiblioteca != null) {
                        System.out.println("ISBN que quieres eliminar");
                        String eliminarISBN = teclado.next();
                        try {
                            miBiblioteca.eliminarLibro(eliminarISBN);
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catálogo no está inicializado");
                        } catch (NoSeEncuentraException e) {
                            System.out.println("El libro no se encuentra para eliminar");
                        }
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;

                case 8:
                    if (miBiblioteca != null) {
                        try {
                            miBiblioteca.exportarLibros();
                            System.out.println("Se ha exportado el catálogo a un fichero.");
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catálogo no está inicializado");
                        } catch (IOException e) {
                            System.out.println("Error al exportar los libros");
                        }
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;

                case 9:
                    if (miBiblioteca != null) {
                        try {
                            miBiblioteca.importarLibros();
                            System.out.println("Se han importado los libros desde el fichero.");
                        } catch (IOException | ClassNotFoundException | ClassCastException e) {
                            System.out.println("Error al importar los libros: " + e.getMessage());
                        } catch (CatalogoNoInicializadoException e) {
                            System.out.println("El catálogo no está inicializado");
                        }
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;


                case 10:
                    if (miBiblioteca != null) {
                        miBiblioteca.listarLibrosDeposito();
                    } else {
                        System.out.println("El catálogo no está inicializado");
                    }
                    break;
            }

        } while (opcion != 11);
    }
}