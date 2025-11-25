package model;

public class autor {

    private int id;
    private String nombre;
    private String nacionalidad;

     public autor() {
    }

    public autor(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;

    }

    public int getId() {
        return this.id;
    }

    public String getnombre() {
        return this.nombre;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }



}