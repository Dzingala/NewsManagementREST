package task.service.impl;

import by.epam.lab.task.entity.Comment;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.CommentsRepository;
import by.epam.lab.task.service.impl.CommentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * @author Ivan Dzinhala
 */
public class TestCommentService {
    @Mock
    private CommentsRepository commentRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void readTest() throws DAOException, ServiceException{
        Comment comment = new Comment();
        Long commentId= 1l;
        comment = commentService.read(commentId);
        Mockito.verify(commentRepository).read(commentId);

    }

    @Test
    public void createTest() throws DAOException, ServiceException {
        Comment comment = new Comment();
        commentService.create(comment);
        Mockito.verify(commentRepository).create(comment);
    }

    @Test
    public void deleteTest() throws DAOException, ServiceException {
        Comment comment = new Comment();
        commentService.delete(comment);
        Mockito.verify(commentRepository).delete(comment.getId());
    }

    @Test
    public void readAllByNewsIdTest() throws DAOException, ServiceException {

        Long newsId = 1L;
        Long comId = 1L;
        Comment comment = new Comment();
        comment.setId(comId);
        ArrayList<Long> listOfCommentId = new ArrayList<>();
        listOfCommentId.add(comId);

        Mockito.when(commentRepository.readCommentsIdByNewsId(newsId)).thenReturn(listOfCommentId);
        Mockito.when(commentRepository.read(comment.getId())).thenReturn(comment);

        commentService.readAllByNewsId(newsId);

        Mockito.verify(commentRepository).readCommentsIdByNewsId(newsId);
        Mockito.verify(commentRepository).read(comment.getId());
    }

    @Test
    public void updateTest()throws DAOException, ServiceException{
        Comment comment = new Comment();
        commentService.update(comment);
        Mockito.verify(commentRepository).update(comment.getId(),comment);
    }
}

