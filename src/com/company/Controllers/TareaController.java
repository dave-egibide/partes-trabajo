/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Controllers;

import com.company.Models.*;

import java.util.List;

/**
 * @author Dave
 */
public abstract class TareaController {

    final static private String nombreTabla = "Tarea";

    public static int getCount() {
        return HibernateController.getCount(nombreTabla);
    }

    public static boolean add(String nombre, String descripcion, String tipo, byte multiMaquina, byte activo) {

        Tarea tarea = new Tarea(nombre, descripcion, tipo, multiMaquina, activo);

        return HibernateController.add(tarea);
    }

    public static boolean delete(Tarea tarea) {
        return HibernateController.delete(tarea);
    }

    public static List getAll() {
        return HibernateController.getAll(nombreTabla);
    }

}
