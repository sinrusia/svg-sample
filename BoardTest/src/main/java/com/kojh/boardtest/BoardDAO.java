/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kojh.boardtest;

import javax.validation.ReportAsSingleViolation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jaehag
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class BoardDAO {
    
    private SessionFactory sessionFactory;
    
    public BoardDAO() {
        
    }
    
    @Autowired
    public BoardDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    public void addBoard(Board board) {
        currentSession().save(board);
    }
    
    public void deleteBoard(String id) {
        currentSession().delete(getBoardById(id));
    }
    
    public Board getBoardById(String id) {
        return (Board)currentSession().get(Board.class, id);
    }
    
    public void saveBoard(Board board) {
        currentSession().update(board);
    }
    
}
