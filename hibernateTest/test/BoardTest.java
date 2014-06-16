/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hibernatetest.Board;
import hibernatetest.BoardManager;
import hibernatetest.HibernateUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaehag
 */
public class BoardTest {
    
    public BoardTest() {
    }
    
    private BoardManager boardManager;
    
    private static String id;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        boardManager = new BoardManager();
        HibernateUtil.beginTransaction();
    }
    
    @After
    public void tearDown() {
        HibernateUtil.closeSession();;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void insertArticle() {
        try {
            Board board = boardManager.insertBoard("제목", "내용");
            
            assertNotSame(board.getId(), 0);
            assertEquals(board.getTitle(), "제목");
            assertEquals(board.getContent(), "내용");
            
            id = board.getId();
            
            HibernateUtil.commit();
        } catch (Throwable ex) {
            HibernateUtil.rollBack();
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void updateBoard() {
    	try {
    		Board board = boardManager.updateBoard(id, "change title", "change contents");
    		
    		assertEquals(board.getId(), id);
    		assertEquals(board.getTitle(), "change title");
    		assertEquals(board.getContent(), "change contents");
    		
    		HibernateUtil.commit();
    	}catch (Throwable ex) {
    		HibernateUtil.rollBack();
    		fail(ex.getMessage());
    	}
    }
    
    @Test
    public void deleteBoard() {
    	try {
    		boardManager.deleteBoard(id);
    		Board board = boardManager.getBoard(id);
    		assertNull(board);
    		
    		HibernateUtil.commit();
    	} catch (Throwable ex) {
    		HibernateUtil.rollBack();
    		fail(ex.getMessage());
    	}
    }
}
