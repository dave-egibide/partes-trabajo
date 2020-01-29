package com.company.Controllers;

import com.company.Models.Maquina;

import java.util.List;

public abstract class MaquinaController {
    final static private String nombreTabla = "Maquina";

    public static int getCount() {
        return HibernateController.getCount(nombreTabla);
    }

    public static Maquina getById(int id) {
        return (Maquina) HibernateController.getById(id, Maquina.class);
    }

    public static boolean delete(int id) {
        return HibernateController.delete(getById(id));
    }

    public static int getReferences(String id) {
        return HibernateController.getReferences(id, nombreTabla);
    }

    public static List getAll() {
        return HibernateController.getAll(nombreTabla);
    }
    public static List getAllActivos() {
        List<Maquina> maquinas = HibernateController.getAll(nombreTabla);
        maquinas.removeIf(maquina -> maquina.getActivo() == 0);
        return maquinas;
    }

}
