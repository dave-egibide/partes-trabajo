package com.company.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Maquina {
    private int id;
    private String nombre;
    private String matricula;
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
    @Column(name = "nombre", nullable = false, length = 10)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "matricula", nullable = true, length = 20)
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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
        Maquina maquina = (Maquina) o;
        return id == maquina.id &&
                activo == maquina.activo &&
                Objects.equals(nombre, maquina.nombre) &&
                Objects.equals(matricula, maquina.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, matricula, activo);
    }

    @OneToMany(mappedBy = "maquinaByIdMaquina")
    public Collection<Parte> getPartesById() {
        return partesById;
    }

    public void setPartesById(Collection<Parte> partesById) {
        this.partesById = partesById;
    }

    @OneToMany(mappedBy = "maquinaByIdMaquina")
    public Collection<TareaMaquina> getTareaMaquinasById() {
        return tareaMaquinasById;
    }

    public void setTareaMaquinasById(Collection<TareaMaquina> tareaMaquinasById) {
        this.tareaMaquinasById = tareaMaquinasById;
    }
}
