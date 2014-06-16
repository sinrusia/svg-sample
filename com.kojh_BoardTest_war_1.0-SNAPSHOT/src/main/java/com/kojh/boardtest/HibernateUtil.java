/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kojh.boardtest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jaehag
 */
public class HibernateUtil {
    
    public static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
        	ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public static Transaction beginTransaction() {
        return getCurrentSession().beginTransaction();
    }
    
    public static void commit() {
        getCurrentSession().getTransaction().commit();;
    }
    
    public static void closeSession() {
        getCurrentSession().close();
    }
    
    public static void rollBack() {
        if (getCurrentSession().isOpen()) {
            Transaction tx = getCurrentSession().getTransaction();
            
            if (tx != null && tx.isActive())
                tx.rollback();;
        }
    }
    
}
