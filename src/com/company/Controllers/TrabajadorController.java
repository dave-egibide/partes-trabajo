package com.company.Controllers;

import com.company.Models.Trabajador;

import java.util.List;

public class TrabajadorController {
    final static private String nombreTabla = Trabajador.getNombreTabla();

    public static int getCount() {
        return HibernateController.getCount(nombreTabla);
    }

    public static boolean add(String dni, String nombre, String apellidos, String puesto) {
        Trabajador trabajador = new Trabajador(dni, nombre, apellidos, puesto);

        return HibernateController.add(trabajador);
    }

    public static Trabajador getById(String id) {
        return (Trabajador) HibernateController.getById(id, Trabajador.class);
    }


    public static boolean modify(String dni, String nombre, String apellidos, String puesto) {
        Trabajador trabajador = new Trabajador(dni, nombre, apellidos, puesto);
        return HibernateController.modify(trabajador);
    }

    public static boolean delete(String id) {
        return HibernateController.delete(getById(id));

    }

    public static int getReferences(String id) {
        return HibernateController.getReferences(id, nombreTabla);
    }

    public static Object[] getValores(Object trabajador) {
        Object valores[] = {((Trabajador) trabajador).getDni(),
                ((Trabajador) trabajador).getNombre(),
                ((Trabajador) trabajador).getApellidos(),
                ((Trabajador) trabajador).getPuesto()};
        return valores;
    }

    public static List<Object> realizarBusqueda(int indexValor, String busqueda) {
        switch (indexValor) {
            case 0: // id
                return HibernateController.realizarBusqueda(busqueda, "id", nombreTabla);
            case 1: // nombre y apellidos
                return HibernateController.realizarBusquedaConcat(busqueda, "nombre", "apellidos", nombreTabla);
            case 2: // puesto
                return HibernateController.realizarBusqueda(busqueda, "puesto", nombreTabla);
        }
        return null;
    }

    public static List getAll() {
        return HibernateController.getAll(nombreTabla);
    }
    public static List getAllActivos() {
        List<Trabajador> trabajadores = HibernateController.getAll(nombreTabla);
        trabajadores.removeIf(trabajador -> trabajador.getActivo() == 0);
        return trabajadores;
    }

}
