package model;

public class libro {

    private String isbn;
    private String titulo;
    private int autor_id;
    private int ano_publicado;
    private int cantidad_total;
    private int cantidad_disponible;

    public libro() {
    }

    public libro(String isbn, String titulo, int autor_id, int ano_publicado, int cantidad_total,
            int cantidad_disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor_id = autor_id;
        this.ano_publicado = ano_publicado;
        this.cantidad_total = cantidad_total;
        this.cantidad_disponible = cantidad_disponible;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getautor_id() {
        return autor_id;
    }

    public int getano_publicado() {
        return ano_publicado;
    }

    public int getcantidad_total() {
        return cantidad_total;
    }

    public int getcantidad_disponible() {
        return cantidad_disponible;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setautor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public void setano_publicado(int ano_publicado) {
        this.ano_publicado = ano_publicado;
    }

    public void setcantidad_total(int cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public void setcantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                " | Título: " + titulo +
                " | Autor ID: " + autor_id +
                " | Año: " + ano_publicado +
                " | Total: " + cantidad_total +
                " | Disponible: " + cantidad_disponible;
    }

}