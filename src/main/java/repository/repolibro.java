package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.conexion;
import model.libro;

public class repolibro {

    public void AgregarLibro(libro libro) {
        String sql = "INSERT INTO libro(isbn,titulo,autor_id,ano_publicado,cantidad_total,cantidad_disponible) VALUES (?,?,?,?,?,?)";
        try (Connection conect = conexion.getConnection();
                PreparedStatement añadir = conect.prepareStatement(sql)) {

            añadir.setString(1, libro.getIsbn());
            añadir.setString(2, libro.getTitulo());
            añadir.setInt(3, libro.getautor_id());
            añadir.setInt(4, libro.getano_publicado());
            añadir.setInt(5, libro.getcantidad_total());
            añadir.setInt(6, libro.getcantidad_disponible());
            añadir.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<libro> ListarLibro() {
        List<libro> libros = new ArrayList<>();
        String sql = "SELECT libro.*, autores.nombre AS autor_nombre FROM libro INNER JOIN autores ON libro.autor_id = autores.id";

        try (Connection conect = conexion.getConnection();
                Statement Lista = conect.createStatement()) {

            ResultSet resultado = Lista.executeQuery(sql);

            while (resultado.next()) {
                libro libro = new libro(
                        resultado.getString("isbn"),
                        resultado.getString("titulo"),
                        resultado.getInt("autor_id"),
                        resultado.getInt("ano_publicado"),
                        resultado.getInt("cantidad_total"),
                        resultado.getInt("cantidad_disponible"));
                libros.add(libro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    public boolean actualizarLibro(libro libro) {
        String sql = "UPDATE libro SET titulo=?, autor_id=?, ano_publicado=?, cantidad_total=?, cantidad_disponible=? WHERE isbn=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement actualizar = conect.prepareStatement(sql)) {

            actualizar.setString(1, libro.getTitulo());
            actualizar.setInt(2, libro.getautor_id());
            actualizar.setInt(3, libro.getano_publicado());
            actualizar.setInt(4, libro.getcantidad_total());
            actualizar.setInt(5, libro.getcantidad_disponible());
            actualizar.setString(6, libro.getIsbn());

            int filas = actualizar.executeUpdate();
            return filas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public libro BuscarLibro(String isbn) {
        String sql = "SELECT * FROM libro WHERE isbn=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement busca = conect.prepareStatement(sql)) {

            busca.setString(1, isbn);
            ResultSet result = busca.executeQuery();

            if (result.next()) {
                return new libro(
                        result.getString("isbn"),
                        result.getString("titulo"),
                        result.getInt("autor_id"),
                        result.getInt("ano_publicado"),
                        result.getInt("cantidad_total"),
                        result.getInt("cantidad_disponible"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public libro BuscarLibro2(String titulo) {
        String sql = "SELECT * FROM libro WHERE titulo=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement busca = conect.prepareStatement(sql)) {

            busca.setString(1, titulo);
            ResultSet result = busca.executeQuery();

            if (result.next()) {
                return new libro(
                        result.getString("isbn"),
                        result.getString("titulo"),
                        result.getInt("autor_id"),
                        result.getInt("ano_publicado"),
                        result.getInt("cantidad_total"),
                        result.getInt("cantidad_disponible"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean EstadoActivo(String isbn) {

        String sql = "SELECT COUNT(*) FROM prestamos WHERE libro_isbn=? AND estado='prestado'";
   
        try (Connection conect = conexion.getConnection();
                PreparedStatement Estado = conect.prepareStatement(sql)) {

            Estado.setString(1, isbn);
            ResultSet result = Estado.executeQuery();

            if (result.next())
                return result.getInt(1) > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(String isbn) {

        if (EstadoActivo(isbn)) {
            System.out.println("No se puede eliminar libros prestados");
            return false;
        }

        String sql = "DELETE FROM libro WHERE isbn=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement eliminar = conect.prepareStatement(sql)) {

            eliminar.setString(1, isbn);
            return eliminar.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
}