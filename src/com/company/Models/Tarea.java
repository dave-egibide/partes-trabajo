package com.company.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Tarea {
    private int id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private byte multiMaquina;
    private byte activo;
    private Collection<Parte> partesById;
    private Collection<TareaMaquina> tareaMaquinasById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "descripcion", nullable = true, length = 200)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "tipo", nullable = false)
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "multi_maquina", nullable = false)
    public byte getMultiMaquina() {
        return multiMaquina;
    }

    public void setMultiMaquina(byte multiMaquina) {
        this.multiMaquina = multiMaquina;
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
        Tarea tarea = (Tarea) o;
        return id == tarea.id &&
                multiMaquina == tarea.multiMaquina &&
                activo == tarea.activo &&
                Objects.equals(nombre, tarea.nombre) &&
                Objects.equals(descripcion, tarea.descripcion) &&
                Objects.equals(tipo, tarea.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, tipo, multiMaquina, activo);
    }

    @OneToMany(mappedBy = "tareaByIdTarea")
    public Collection<Parte> getPartesById() {
        return partesById;
    }

    public void setPartesById(Collection<Parte> partesById) {
        this.partesById = partesById;
    }

    @OneToMany(mappedBy = "tareaByIdTarea")
    public Collection<TareaMaquina> getTareaMaquinasById() {
        return tareaMaquinasById;
    }

    public void setTareaMaquinasById(Collection<TareaMaquina> tareaMaquinasById) {
        this.tareaMaquinasById = tareaMaquinasById;
    }
}
