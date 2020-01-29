/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Controllers;

import com.company.Models.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dave
 */
public abstract class TareaController {

    final static private String nombreTabla = "Tarea";

    public static int getCount() {
        return HibernateController.getCount(nombreTabla);
    }

    public static boolean delete(Tarea tarea) {
        return HibernateController.delete(tarea);
    }

    public static List getAll() {
        return HibernateController.getAll(nombreTabla);
    }

    public static List<Maquina> getMaquinas(Tarea tarea) {
        Collection<TareaMaquina> tareaMaquinas = tarea.getTareaMaquinasById();
        List<Maquina> maquinas = new ArrayList<>();
        for (TareaMaquina tareaMaquina:tareaMaquinas) {
            maquinas.add(tareaMaquina.getMaquinaByIdMaquina());
        }
        return maquinas;
    }
    public static Tarea getById(int id) {
        return (Tarea) HibernateController.getById(id, Tarea.class);
    }
    public static List getAllActivos() {
        List<Tarea> tareas = HibernateController.getAll(nombreTabla);
        tareas.removeIf(tarea -> tarea.getActivo() == 0);
        return tareas;
    }
}
