/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dave
 */
public abstract class HibernateController {

    public static boolean conectar() {
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            //session.beginTransaction();
            //session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
            return false;
        }
        return session.isConnected();
    }

    public static int getCount(String nombreTabla) {
        String hqlQuery = "select count(*) from " + nombreTabla;
        return executeHQLQueryForInt(hqlQuery);
    }

    public static Object getByIndex(int indice, String nombreTabla) {
        List list = getAll(nombreTabla);
        return list.get(indice - 1);
    }

    public static Object getLast(String nombreTabla) {
        List list = getAll(nombreTabla);
        return list.get(list.size() - 1);
    }

    private static int executeHQLQueryForInt(String hqlQuery) {
        int result;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query q = session.createQuery(hqlQuery);
            Iterator count = q.iterate();
            result = Integer.parseInt(count.next().toString());
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException he) {
            he.printStackTrace();
            result = Integer.MIN_VALUE;
        } catch (NullPointerException e) {
            result = Integer.MIN_VALUE;
        }
        return result;
    }

    public static List getAll(String nombreTabla) {
        List result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from " + nombreTabla);
            result = q.list();
            session.getTransaction().commit();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

    public static Object getById(String codigo, Class clase) {
        Object objeto = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            objeto = (Object) session.get(clase, codigo);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return objeto;
    }

    public static boolean add(Object objeto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.saveOrUpdate(objeto);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return false;
    }

    static boolean modify(Object objeto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(objeto);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return false;
    }

    static boolean delete(Object objeto) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(objeto);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return false;
    }

    static int getReferences(String codigo, String nombreTabla) {
        String hqlQuery = "select count(*) from Gestion g "
                + "where g." + nombreTabla.toLowerCase() + " = '" + codigo + "'";
        return executeHQLQueryForInt(hqlQuery);
    }

    static List<Object> realizarBusqueda(String busqueda, String atributo, String nombreTabla) {
        List result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from " + nombreTabla
                    + " where lower(" + atributo + ") like lower(:busqueda)");
            q.setParameter("busqueda", "%" + busqueda + "%");
            result = q.list();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

    static List<Object> realizarBusquedaConcat(String busqueda, String atributo1,
            String atributo2, String nombreTabla) {
        List result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("from " + nombreTabla + " "
                    + "where lower(concat(" + atributo1 + ",' '," + atributo2 + "))"
                    + " like lower(:busqueda)");
            q.setParameter("busqueda", "%" + busqueda + "%");
            result = q.list();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

    static Object get(Object id, Class clase) {
        Object resultado = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            resultado = session.get(clase, (Serializable) id);
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return resultado;
    }

    static List<Object> getReferencias(String codigo, String resultado, String tabla, String fuente, boolean repetidos) {
        List result = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String distinct = "";
            if (!repetidos) {
                distinct = " distinct";
            }
            Query q = session.createQuery("select" + distinct + " g." + resultado + " "
                    + "from " + tabla + " g "
                    + "where g." + fuente + ".id"
                    + " like :codigo");
            q.setParameter("codigo", codigo);
            result = q.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

    static Object getSum(String codigo, String resultado, String tabla, String fuente) {
        //select sum(g.cantidad) from Gestion g where g.piezas like "PIE01"
        double sum = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("select sum(g." + resultado + ") "
                    + "from " + tabla + " g "
                    + "where g." + fuente
                    + " like :codigo");
            q.setParameter("codigo", codigo);
            Iterator result = q.iterate();
            session.getTransaction().commit();
            sum = (double) result.next();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
        } catch (NullPointerException e) {
        }
        return sum;
    }

    static List gestionAgregados(String resultado, String tabla, String fuente, String funcion, Boolean distinto) {
        String distinct = "";
        if (distinto) {
            distinct = "distinct ";
        }
        List result = null;
        //select g.piezas, sum(g.cantidad) from Gestion g group by g.piezas order by sum(g.cantidad) desc
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query q = session.createQuery("select g." + fuente
                    + " , " + funcion + "(" + distinct + "g." + resultado + ") "
                    + "from " + tabla + " g "
                    + "group by g." + fuente + " "
                    + "order by " + funcion + "(g." + resultado + ") desc");
            result = q.list();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }

}
