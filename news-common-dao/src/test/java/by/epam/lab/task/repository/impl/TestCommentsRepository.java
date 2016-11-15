package by.epam.lab.task.repository.impl;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.entity.News;
import by.epam.lab.task.exceptions.dao.DAOException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author Ivan Dzinhala
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestCommentsRepository {
    @Autowired
    private CommentsRepositoryImpl commentDAO;
    private static final String tempCommentText="tempcommenttext";
    @Test
    public void create() throws DAOException {
        Long commentId=commentDAO.create(new Comment(1L,1L,tempCommentText,new Timestamp(new java.util.Date().getTime())));
        assertNotNull(commentId);
    }

    @Test
    public void read() throws DAOException {
        Comment comment = commentDAO.read(3L);
        assertNotNull(comment);
    }

    @Test
    public void update() throws DAOException {
        Long commentId = 3L;
        Comment comment = commentDAO.read(commentId);
        comment.setText(tempCommentText);
        commentDAO.update(commentId, comment);
        Comment commentExpected = commentDAO.read(commentId);
        assertTrue(comment.equals(commentExpected));
    }

    @Test(expected = DAOException.class)
    public void delete() throws DAOException {
        Long commentId = 2L;
        commentDAO.delete(commentId);
        assertNull(commentDAO.read(commentId));
    }

    @Test
    public void findCommentsIdByNewsId() throws DAOException {
        Long newsId = 4L;
        List<Long> commentsId = commentDAO.readCommentsIdByNewsId(newsId);
        assertNotNull(commentsId);
    }
    @Test
    public void readAll() throws DAOException{
        List<Comment> comments=commentDAO.readAll();
        assertFalse(comments.isEmpty());
    }

}

