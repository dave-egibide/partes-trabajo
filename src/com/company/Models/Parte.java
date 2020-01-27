package com.company.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Parte {
    private int id;
    private Date fecha;
    private int minutos;
    private Trabajador trabajadorByIdTrabajador;
    private Tarea tareaByIdTarea;
    private Maquina maquinaByIdMaquina;
    private Pedido pedidoByIdPedido;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "minutos", nullable = false)
    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parte parte = (Parte) o;
        return id == parte.id &&
                minutos == parte.minutos &&
                Objects.equals(fecha, parte.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, minutos);
    }

    @ManyToOne
    @JoinColumn(name = "id_trabajador", referencedColumnName = "id", nullable = false)
    public Trabajador getTrabajadorByIdTrabajador() {
        return trabajadorByIdTrabajador;
    }

    public void setTrabajadorByIdTrabajador(Trabajador trabajadorByIdTrabajador) {
        this.trabajadorByIdTrabajador = trabajadorByIdTrabajador;
    }

    @ManyToOne
    @JoinColumn(name = "id_tarea", referencedColumnName = "id", nullable = false)
    public Tarea getTareaByIdTarea() {
        return tareaByIdTarea;
    }

    public void setTareaByIdTarea(Tarea tareaByIdTarea) {
        this.tareaByIdTarea = tareaByIdTarea;
    }

    @ManyToOne
    @JoinColumn(name = "id_maquina", referencedColumnName = "id")
    public Maquina getMaquinaByIdMaquina() {
        return maquinaByIdMaquina;
    }

    public void setMaquinaByIdMaquina(Maquina maquinaByIdMaquina) {
        this.maquinaByIdMaquina = maquinaByIdMaquina;
    }

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    public Pedido getPedidoByIdPedido() {
        return pedidoByIdPedido;
    }

    public void setPedidoByIdPedido(Pedido pedidoByIdPedido) {
        this.pedidoByIdPedido = pedidoByIdPedido;
    }
}
