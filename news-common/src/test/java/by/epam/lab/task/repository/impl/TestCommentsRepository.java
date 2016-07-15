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
    private NewsRepositoryImpl newsDAO;
    @Autowired
    private CommentsRepositoryImpl commentDAO;
    private static final String tempCommentText="tempcommenttext";
    @Test
    public void createTest() throws DAOException {
        Long newsId=newsDAO.create(new News(
                1,
                "tempTitle",
                "tempShortText",
                "tempFullText",
                new Timestamp(new java.util.Date().getTime()),
                new java.sql.Date(Calendar.getInstance().getTime().getTime())));
        Comment comment = new Comment();
        comment.setNewsId(newsId);
        comment.setText(tempCommentText);
        comment.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        Long commentId = commentDAO.create(comment);
    }

    @Test
    public void readTest() throws DAOException {
        Comment comment = commentDAO.read(3L);
        assertNotNull(comment);
    }

    @Test
    public void updateTest() throws DAOException {
        Long commentId = 3L;
        Comment comment = commentDAO.read(commentId);
        comment.setText(tempCommentText);
        commentDAO.update(commentId, comment);
        Comment commentExpected = commentDAO.read(commentId);
        assertTrue(comment.equals(commentExpected));
    }

    @Test(expected = DAOException.class)
    public void deleteTest() throws DAOException {
        Long commentId = 2L;
        commentDAO.delete(commentId);
        assertNull(commentDAO.read(commentId));
    }

    @Test
    public void findCommentsIdByNewsIdTest() throws DAOException {
        Long newsId = 4L;
        List<Long> commentsId = commentDAO.readCommentsIdByNewsId(newsId);
        assertNotNull(commentsId);
    }
    @Test
    public void readAllTest() throws DAOException{
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        comment.setNewsId(1l);
        comment.setText(tempCommentText);
        Long commentId=commentDAO.create(comment);
        List<Comment> comments=commentDAO.readAll();
        assertFalse(comments.isEmpty());
    }

}

