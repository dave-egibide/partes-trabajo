package com.company.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Trabajador {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String puesto;
    private byte activo;
    private Collection<Parte> partesById;
    private static final String NOMBRETABLA = "Trabajador";

    public Trabajador(String dni, String nombre, String apellidos, String puesto) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
    }

    public Trabajador() {
    }

    public static String getNombreTabla() {
        return NOMBRETABLA;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dni", nullable = false, length = 9)
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "apellidos", nullable = false, length = 50)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "puesto", nullable = true, length = 20)
    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Basic
    @Column(name = "activo", nullable = false)
    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trabajador that = (Trabajador) o;
        return id == that.id &&
                activo == that.activo &&
                Objects.equals(dni, that.dni) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(apellidos, that.apellidos) &&
                Objects.equals(puesto, that.puesto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, nombre, apellidos, puesto, activo);
    }

    @OneToMany(mappedBy = "trabajadorByIdTrabajador")
    public Collection<Parte> getPartesById() {
        return partesById;
    }

    public void setPartesById(Collection<Parte> partesById) {
        this.partesById = partesById;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", puesto='" + puesto + '\'' +
                ", activo=" + activo +
                ", partesById=" + partesById +
                '}';
    }
}
