package com.company.Models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Pedido {
    private int id;
    private String nombre;
    private byte activo;
    private Collection<Parte> partesById;

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
        Pedido pedido = (Pedido) o;
        return id == pedido.id &&
                activo == pedido.activo &&
                Objects.equals(nombre, pedido.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, activo);
    }

    @OneToMany(mappedBy = "pedidoByIdPedido")
    public Collection<Parte> getPartesById() {
        return partesById;
    }

    public void setPartesById(Collection<Parte> partesById) {
        this.partesById = partesById;
    }
}
