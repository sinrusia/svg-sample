/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kojh.boardtest;

import java.io.Serializable;

import org.hibernate.Session;

/**
 *
 * @author jaehag
 */
public class BoardManager {
    
    public Board insertBoard(String title, String content) {
        Session session = HibernateUtil.getCurrentSession();
        
        Board board = new Board();
        //board.setId(11);
        board.setTitle(title);
        board.setContent(content);
        session.save(board);
        
        return board;
    }
    
    public Board updateBoard(String id, String newTitle, String newContent) {
        Board board = getBoard(id);
        board.setTitle(newTitle);
        board.setContent(newContent);
        
        return board;
    }
    
    public void deleteBoard(String id) {
        Session session = HibernateUtil.getCurrentSession();
        Board board = getBoard(id);
        session.delete(board);
    }
    
    public Board getBoard(String id) {
        Session session = HibernateUtil.getCurrentSession();
        return (Board)session.get(Board.class, id);
    }
}
