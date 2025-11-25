package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import db.conexion;
import model.Socio;

public class reposocio {

    public boolean agregarSocio(Socio socio) {
        String sql = "INSERT INTO socios(nombre, apellido, dni, telefono) VALUES (?, ?, ?, ?)";

        try (Connection conect = conexion.getConnection();
                PreparedStatement Agregar = conect.prepareStatement(sql)) {

            Agregar.setString(1, socio.getNombre());
            Agregar.setString(2, socio.getApellido());
            Agregar.setString(3, socio.getDni());
            Agregar.setString(4, socio.getTelefono());
            Agregar.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Socio> listarSocios() {
        ArrayList<Socio> lista = new ArrayList<>();
        String sql = "SELECT * FROM socios";

        try (Connection conect = conexion.getConnection();
                Statement Listar = conect.createStatement();
                ResultSet result = Listar.executeQuery(sql)) {

            while (result.next()) {
                Socio socio = new Socio(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("dni"),
                        result.getString("telefono"));
                lista.add(socio);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Socio buscarPorDni(String dni) {
        String sql = "SELECT * FROM socios WHERE dni = ?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement buscar = conect.prepareStatement(sql)) {

            buscar.setString(1, dni);
            ResultSet result = buscar.executeQuery();

            if (result.next()) {
                return new Socio(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("apellido"),
                        result.getString("dni"),
                        result.getString("telefono"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null; 
    }

    public boolean actualizarSocio(Socio socio) {
        String sql = "UPDATE socios SET nombre = ?, apellido = ?, dni = ?, telefono = ? WHERE id = ?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement actualizar = conect.prepareStatement(sql)) {

            actualizar.setString(1, socio.getNombre());
            actualizar.setString(2, socio.getApellido());
            actualizar.setString(3, socio.getDni());
            actualizar.setString(4, socio.getTelefono());
            actualizar.setInt(5, socio.getId());

            int filas = actualizar.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarSocio(int id) {
        String sqlChecar = "SELECT COUNT(*) FROM prestamos WHERE id = ? AND estado = 'prestado'";
        String sqlDelete = "DELETE FROM socios WHERE id = ?";

        try (Connection conect = conexion.getConnection()) {

            PreparedStatement chekar = conect.prepareStatement(sqlChecar);
            chekar.setInt(1, id);
            ResultSet result = chekar.executeQuery();

            if (result.next() && result.getInt(1) > 0) {
                System.out.println("El socio tiene préstamos activos.");
                return false;
            }

            PreparedStatement Eliminar = conect.prepareStatement(sqlDelete);
            Eliminar.setInt(1, id);
            Eliminar.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}