/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.kojh.boardtest.Board;
import com.kojh.boardtest.BoardDAO;
import com.kojh.boardtest.HibernateUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author jaehag
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BoardTest {
    
    public BoardTest() {
    }
    
    @Autowired
    private ApplicationContext context;
    
    private BoardDAO boardDAO;
    
    private static String id;

    
    @Before
    public void setUp() {
        boardDAO = context.getBean("boardDao", BoardDAO.class);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void insertArticle() {
        try {
            Board board = new Board();
            board.setTitle("제목");
            board.setContent("내용");
            boardDAO.addBoard(board);
            
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
            
    		Board board = boardDAO.getBoardById(id);
                board.setTitle("change title");
                boardDAO.saveBoard(board);
                
                board = boardDAO.getBoardById(id);
    		
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
    		boardDAO.deleteBoard(id);
    		Board board = boardDAO.getBoardById(id);
    		assertNull(board);
    		
    		HibernateUtil.commit();
    	} catch (Throwable ex) {
    		HibernateUtil.rollBack();
    		fail(ex.getMessage());
    	}
    }
}
