package com.company.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tarea_maquina", schema = "partes_trabajo", catalog = "")
public class TareaMaquina {
    private int id;
    private Tarea tareaByIdTarea;
    private Maquina maquinaByIdMaquina;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TareaMaquina that = (TareaMaquina) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
    @JoinColumn(name = "id_maquina", referencedColumnName = "id", nullable = false)
    public Maquina getMaquinaByIdMaquina() {
        return maquinaByIdMaquina;
    }

    public void setMaquinaByIdMaquina(Maquina maquinaByIdMaquina) {
        this.maquinaByIdMaquina = maquinaByIdMaquina;
    }
}
