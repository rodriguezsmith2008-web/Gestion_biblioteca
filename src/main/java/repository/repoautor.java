package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.conexion;
import model.autor;

public class repoautor {

    public int RegistrarAutor(autor autor) {
        String sql = "INSERT INTO autores(nombre,nacionalidad) VALUES (?,?)";
        try (Connection conect = conexion.getConnection();
                PreparedStatement registrar = conect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            registrar.setString(1, autor.getnombre());
            registrar.setString(2, autor.getNacionalidad());
            registrar.executeUpdate();

            ResultSet rs = registrar.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; 
    }

    public List<autor> ListarAutores() {
        List<autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";

        try (Connection conect = conexion.getConnection();
                Statement lista = conect.createStatement()) {

            ResultSet result = lista.executeQuery(sql);

            while (result.next()) {
                autor autorTemp = new autor(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("nacionalidad"));
                autores.add(autorTemp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public boolean actualizar(autor autor) {
        String sql = "UPDATE autores SET nombre=?, nacionalidad=? WHERE id=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement actualizar = conect.prepareStatement(sql)) {

            actualizar.setString(1, autor.getnombre());
            actualizar.setString(2, autor.getNacionalidad());
            actualizar.setInt(3, autor.getId());

            return actualizar.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public autor BuscarAutor(int id) {
        String sql = "SELECT * FROM autores WHERE id=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement Busca = conect.prepareStatement(sql)) {

            Busca.setInt(1, id);
            ResultSet result = Busca.executeQuery();

            if (result.next()) {
                return new autor(
                        result.getInt("id"),
                        result.getString("nombre"),
                        result.getString("nacionalidad"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean EliminarAutor(int id) {

        String sqlCount = "SELECT COUNT(*) FROM libro WHERE autor_id=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement Eliminar = conect.prepareStatement(sqlCount)) {

            Eliminar.setInt(1, id);
            ResultSet result = Eliminar.executeQuery();

            if (result.next() && result.getInt(1) > 0) {
                System.out.println("El autor tiene libros asociados");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String sqlDelete = "DELETE FROM autores WHERE id=?";

        try (Connection conect = conexion.getConnection();
                PreparedStatement Eliminar = conect.prepareStatement(sqlDelete)) {

            Eliminar.setInt(1, id);
            return Eliminar.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}