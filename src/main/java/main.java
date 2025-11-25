import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import model.Socio;
import model.autor;
import model.libro;
import model.prestamo;
import repository.repoautor;
import repository.repolibro;
import repository.repoprestamo;
import repository.reposocio;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        repoautor repoautor = new repoautor();
        repolibro repolibro = new repolibro();
        reposocio reposocio = new reposocio();
        repoprestamo repoprestamo = new repoprestamo();

        int opcionM;

        do {
            System.out.println("\n---SISTEMA DE BIBLIOTECA --");
            System.out.println("1.Gestión de Libros");
            System.out.println("2.Gestion de Socios");
            System.out.println("3.Prestamos y Devoluciones");
            System.out.println("4.Gestion de aautores");
            System.out.println("0.Salir");
            System.out.print("Seleccione opción: ");
            opcionM = scanner.nextInt();
            scanner.nextLine();

            switch (opcionM) {

                case 1:
                    int opcionLibro;
                    do {
                        System.out.println("\n--- GESTION DE LIBROS ---");
                        System.out.println("1.Añadir libro");
                        System.out.println("2.Buscar por ISBN");
                        System.out.println("3.Buscar por título");
                        System.out.println("4.Listar libros");
                        System.out.println("5.Modificar libro");
                        System.out.println("6.Eliminar libro");
                        System.out.println("0.Volver");
                        System.out.print("Seleccione la opcion que quieres: ");
                        opcionLibro = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionLibro) {
                            case 1:
                                System.out.print("ISBN ");
                                String isbn = scanner.nextLine();
                                System.out.print("Título ");
                                String titulo = scanner.nextLine();
                                System.out.print("Año publicado ");
                                int ano = scanner.nextInt();
                                System.out.print("Cantidad total ");
                                int total = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("¿Autor existente? (1.Si / 2.No): ");
                                int respuesta = scanner.nextInt();
                                int autorId;

                                switch (respuesta) {
                                    case 1:
                                        System.out.print("ID del autor ");
                                        autorId = scanner.nextInt();
                                        scanner.nextLine();
                                        break;
                                    default:
                                        scanner.nextLine();
                                        System.out.print("Nombre autor ");
                                        String nombre = scanner.nextLine();
                                        System.out.print("Nacionalid ");
                                        String nacionalidad = scanner.nextLine();
                                        autorId = repoautor.RegistrarAutor(new autor(0, nombre, nacionalidad));
                                }

                                repolibro.AgregarLibro(new libro(isbn, titulo, autorId, ano, total, total));
                                System.out.println("Libro registrado correctamente");
                                break;

                            case 2:
                                System.out.print("Ingrese isbn ");
                                String Isbn = scanner.nextLine();
                                libro libro = repolibro.BuscarLibro(Isbn);
                                switch (libro != null ? 1 : 0) {
                                    case 1:
                                        System.out.println(libro);
                                        break;
                                    case 0:
                                        System.out.println("No encontrado.");
                                        break;
                                }
                                break;

                            case 3:
                                System.out.print("Ingrese título ");
                                String titulo2 = scanner.nextLine();
                                libro libro2 = repolibro.BuscarLibro2(titulo2);
                                switch (libro2 != null ? 1 : 0) {
                                    case 1:
                                        System.out.println(libro2);
                                        break;
                                    case 0:
                                        System.out.println("No encontrado");
                                        break;
                                }
                                break;

                            case 4:
                                List<libro> lista = repolibro.ListarLibro();
                                for (libro list : lista)
                                    System.out.println(list);
                                break;

                            case 5:
                                System.out.print("ISBN del libro a modificar: ");
                                isbn = scanner.nextLine();
                                libro modificado = repolibro.BuscarLibro(isbn);
                                switch (modificado != null ? 1 : 0) {
                                    case 1:
                                        System.out.print("Nuevo título ");
                                        modificado.setTitulo(scanner.nextLine());
                                        System.out.print("Nuevo id de autor ");
                                        modificado.setautor_id(scanner.nextInt());
                                        System.out.print("Nuevo añ");
                                        modificado.setano_publicado(scanner.nextInt());
                                        System.out.print("Nueva cantidad total ");
                                        modificado.setcantidad_total(scanner.nextInt());
                                        System.out.print("Nueva cantidad disponibl");
                                        modificado.setcantidad_disponible(scanner.nextInt());
                                        scanner.nextLine();
                                        repolibro.actualizarLibro(modificado);
                                            System.out.println("Libro actualizado.");

                                        break;
                                }
                                break;

                            case 6:
                                System.out.print("ISBN a eliminar ");
                                isbn = scanner.nextLine();
                                repolibro.eliminar(isbn);
                                System.out.println("Eliminación exitosa");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }

                    } while (opcionLibro != 0);
                    break;

                case 2:
                    int opcionSocio;
                    do {
                        System.out.println("\n--- GESTIoN DE SOCIOS ---");
                        System.out.println("1. Registrar socio");
                        System.out.println("2. Buscar por DNI");
                        System.out.println("3. Listar socios");
                        System.out.println("4. Modificar socio");
                        System.out.println("5. Eliminar socio");
                        System.out.println("0. Volver");
                        System.out.print("Seleccione tu opcion ");
                        opcionSocio = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionSocio) {
                            case 1:
                                System.out.print("Nombre: ");
                                String nombre = scanner.nextLine();
                                System.out.print("Apellido: ");
                                String apellido = scanner.nextLine();
                                System.out.print("DNI: ");
                                String dni = scanner.nextLine();
                                System.out.print("Teléfono: ");
                                String telefono = scanner.nextLine();
                                reposocio.agregarSocio(new Socio(0, nombre, apellido, dni, telefono));

                                System.out.println("Socio registrado.");
                                break;

                            case 2:
                                System.out.print("DNI: ");
                                dni = scanner.nextLine();
                                Socio socio = reposocio.buscarPorDni(dni);
                                switch (socio != null ? 1 : 0) {
                                    case 1:
                                        System.out.println(socio);
                                        break;
                                    case 0:
                                        System.out.println("No encontrado.");
                                        break;
                                }
                                break;

                            case 3:
                                List<Socio> listaSocios = reposocio.listarSocios();
                                for (Socio list : listaSocios)
                                    System.out.println(list);
                                break;

                            case 4:
                                System.out.print("ID del socio: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                Socio modificado = null;
                                for (Socio socio2 : reposocio.listarSocios())
                                    if (socio2.getId() == id)
                                        modificado = socio2;
                                switch (4) {
                                    case 1:
                                        System.out.print("Nuevo nombre: ");
                                        modificado.setNombre(scanner.nextLine());
                                        System.out.print("Nuevo apellido: ");
                                        modificado.setApellido(scanner.nextLine());
                                        System.out.print("Nuevo DNI: ");
                                        modificado.setDni(scanner.nextLine());
                                        System.out.print("Nuevo teléfono: ");
                                        modificado.setTelefono(scanner.nextLine());
                                        reposocio.actualizarSocio(modificado);

                                        System.out.println("Socio actualizado.");

                                        break;
                                    case 0:
                                        System.out.println("No encontrado.");
                                        break;
                                }
                                break;

                            case 5:
                                System.out.print("ID a eliminar: ");
                                int eid = scanner.nextInt();
                                scanner.nextLine();
                                reposocio.eliminarSocio(eid);

                                System.out.println("Socio eliminado.");
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }

                    } while (opcionSocio != 0);
                    break;

                case 3:
                    int opcionPrestamo;
                    do {
                        System.out.println("\n--- PRÉSTAMOS Y DEVOLUCIONES ---");
                        System.out.println("1. Registrar préstamo");
                        System.out.println("2. Devolver libro");
                        System.out.println("3. Listar prestamos activos");
                        System.out.println("4. Listar prestamos por sociov");
                        System.out.println("5. Ver libros prestados actualmente");
                        System.out.println("0. Volver");
                        System.out.print("Seleccione la opcion: ");
                        opcionPrestamo = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionPrestamo) {
                            case 1:
                                System.out.print("ISBN: ");
                                String isbn = scanner.nextLine();
                                System.out.print("ID socio: ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Fecha prestamo (Año-Mes-Dia): ");
                                Date fecha1 = Date.valueOf(scanner.nextLine());
                                System.out.print("Fecha devolución (Año-Mes-Dia): ");
                                Date fecha2 = Date.valueOf(scanner.nextLine());
                                prestamo prestamo = new prestamo(0, isbn, id, fecha1, fecha2, null, "prestado");

                                boolean registrado = repoprestamo.registrarPrestamo(prestamo);

                                if (registrado) {
                                    System.out.println("Préstamo registrado correctamente.");
                                } else {
                                    System.out.println("No se pudo registrar el préstamo.");
                                }
                                break;

                            case 2:
                                System.out.print("ID prestamo: ");
                                id = scanner.nextInt();
                                scanner.nextLine();
                                repoprestamo.devolverPrestamo(id);

                                System.out.println("Libro devuelto.");
                                break;

                            case 3:
                                List<prestamo> actuales = repoprestamo.librosPrestadosActuales();
                                for (prestamo act : actuales)
                                    System.out.println(act);
                                break;

                            case 4:
                                System.out.print("ID socio: ");
                                int prestamoP = scanner.nextInt();
                                scanner.nextLine();
                                List<prestamo> listapPrestamos = repoprestamo.listarPrestamos();

                                for (prestamo prestado : listapPrestamos) {
                                    if (prestado.getSocio() == prestamoP)
                                        System.out.println(prestado);
                                }
                                break;

                            case 5:
                                List<prestamo> listaPrestamos2 = repoprestamo.librosPrestadosActuales();
                                for (prestamo s2 : listaPrestamos2)
                                    System.out.println(s2);
                                break;

                            case 0:
                                break;

                            default:
                                System.out.println("Opción inválida.");
                        }

                    } while (opcionPrestamo != 0);
                    break;

                case 4:
                    int opcionAutor;
                    do {
                        System.out.println("\n--- GESTIÓN DE AUTORES ---");
                        System.out.println("1. Registrar autor");
                        System.out.println("2. Listar autores");
                        System.out.println("3. Buscar autor por ID");
                        System.out.println("4. Modificar autor");
                        System.out.println("5. Eliminar autor");
                        System.out.println("0. Volver");
                        System.out.print("Seleccione opción: ");
                        opcionAutor = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionAutor) {
                            case 1:
                                System.out.print("Nombre del autor ");
                                String nombre = scanner.nextLine();
                                System.out.print("Nacionalidad ");
                                String nacionalidad = scanner.nextLine();
                                int autorId = repoautor.RegistrarAutor(new autor(0, nombre, nacionalidad));
                                System.out.println("Autor registrado el id es  " + autorId);
                                break;
                            case 2:
                                List<autor> listaAutores = repoautor.ListarAutores();
                                for (autor a : listaAutores)
                                    System.out.println("ID: " + a.getId() + " | Nombre: " + a.getnombre()
                                            + " | Nacionalidad: " + a.getNacionalidad());

                                break;
                            case 3:
                                System.out.print("ID del autor a buscar: ");
                                int idBuscar = scanner.nextInt();
                                scanner.nextLine();
                                autor autorBuscado = repoautor.BuscarAutor(idBuscar);
                                if (autorBuscado != null) {
                                    System.out.println(
                                            "ID: " + autorBuscado.getId() + " | Nombre: " + autorBuscado.getnombre()
                                                    + " | Nacionalidad: " + autorBuscado.getNacionalidad());
                                } else {
                                    System.out.println("Autor no encontrado.");
                                }
                                break;
                            case 4:
                                System.out.print("ID del autor a modificar ");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                autor autorModificar = repoautor.BuscarAutor(id);
                                if (autorModificar != null) {
                                    System.out.print("Nuevo nombre ");
                                    autorModificar.setnombre(scanner.nextLine());
                                    System.out.print("Nueva nacionalidad ");
                                    autorModificar.setNacionalidad(scanner.nextLine());
                                    repoautor.actualizar(autorModificar);
                                    System.out.println("Autor modificado");
                                }
                                break;
                            case 5:
                                System.out.print("ID del autor a eliminar: ");
                                int idEliminar = scanner.nextInt();
                                scanner.nextLine();
                                repoautor.EliminarAutor(idEliminar);
                                System.out.println("Autor eliminado.");
                                break;
                            case 0:
                                break;
                            default:
                                System.out.println("Opción inválida.");
                                break;
                        }

                    } while (opcionAutor != 0);
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }

        } while (opcionM != 0);

        scanner.close();
    }
}