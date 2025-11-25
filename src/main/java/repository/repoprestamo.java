package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import db.conexion;
import model.prestamo;

public class repoprestamo {

    public boolean registrarPrestamo(prestamo prestamo) {
        String sqlInsert = "INSERT INTO prestamos(libro_isbn, socio_id, fecha_prestamo, fecha_devolucion_prevista, estado) VALUES (?, ?, ?, ?, 'prestado')";
        String sqlUpdate = "UPDATE libro SET cantidad_disponible = cantidad_disponible - 1 WHERE isbn = ? AND cantidad_disponible > 0";

        try (Connection conect = conexion.getConnection()) {

            PreparedStatement Registrar = conect.prepareStatement(sqlUpdate);
            Registrar.setString(1, prestamo.getLibro());
            int filas = Registrar.executeUpdate();

            if (filas == 0) {
                System.out.println("No hay unidades disponibles del libro.");
                return false;
            }

            PreparedStatement Agregar = conect.prepareStatement(sqlInsert);
            Agregar.setString(1, prestamo.getLibro());
            Agregar.setInt(2, prestamo.getSocio());
            Agregar.setDate(3, prestamo.getFechaPrestamo());
            Agregar.setDate(4, prestamo.getFechaDevolucionPrevista());

            Agregar.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean devolverPrestamo(int id) {
        String sqlUpdatePrestamo = "UPDATE prestamos SET estado='devuelto', fecha_devolucion_real = CURDATE() WHERE id = ? AND estado='prestado'";
        String sqlUpdateLibro = "UPDATE libro SET cantidad_disponible = cantidad_disponible + 1 WHERE isbn = (SELECT libro_isbn FROM prestamos WHERE id = ?)";

        try (Connection conect = conexion.getConnection()) {

            PreparedStatement Devolver = conect.prepareStatement(sqlUpdatePrestamo);
            Devolver.setInt(1, id);
            int filas = Devolver.executeUpdate();

            if (filas == 0) {
                System.out.println("El préstamo no existe o ya está devuelto.");
                return false;
            }

            PreparedStatement devolver = conect.prepareStatement(sqlUpdateLibro);
            devolver.setInt(1, id);
            devolver.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<prestamo> listarPrestamos() {
        ArrayList<prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conect = conexion.getConnection();
                Statement Listar = conect.createStatement();
                ResultSet result = Listar.executeQuery(sql)) {

            while (result.next()) {
                prestamo p = new prestamo(
                        result.getInt("id"),
                        result.getString("libro_isbn"),
                        result.getInt("socio_id"),
                        result.getDate("fecha_prestamo"),
                        result.getDate("fecha_devolucion_prevista"),
                        result.getDate("fecha_devolucion_real"),
                        result.getString("estado"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public ArrayList<prestamo> librosPrestadosActuales() {
        ArrayList<prestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";

        try (Connection conect = conexion.getConnection();
                Statement Actual = conect.createStatement();
                ResultSet result = Actual.executeQuery(sql)) {

            while (result.next()) {

                prestamo prestamo = new prestamo(

                        result.getInt("id"),
                        result.getString("libro_isbn"),
                        result.getInt("socio_id"),
                        result.getDate("fecha_prestamo"),
                        result.getDate("fecha_devolucion_prevista"),
                        result.getDate("fecha_devolucion_real"), 
                        result.getString("estado"));

                lista.add(prestamo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}