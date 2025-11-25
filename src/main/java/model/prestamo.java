package model;

import java.sql.Date;

public class prestamo {

    private int id;
    private String libro_id;
    private int socio_id;
    private Date fechaPrestamo;
    private Date fechaDevolucionPrevista;
    private Date fechaDevolucionReal;
    private String estado;

    public prestamo() {
    }

    public prestamo(int id, String libro_id, int socio_id, Date fechaPrestamo,
            Date fechaDevolucionPrevista, Date fechaDevolucionReal,
            String estado) {
        this.id = id;
        this.libro_id = libro_id;
        this.socio_id = socio_id;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getLibro() {
        return libro_id;
    }

    public int getSocio() {
        return socio_id;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public Date getFechaDevolucionPrevista() {
        return fechaDevolucionPrevista;
    }

    public Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Libro: " + libro_id +
                " | Socio ID: " + socio_id +
                " | Fecha préstamo: " + fechaPrestamo +
                " | Fecha devolución prevista: " + fechaDevolucionPrevista +
                " | Fecha devolución real: " + fechaDevolucionReal +
                " | Estado: " + estado;
    }

}