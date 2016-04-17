package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.CommentsRepository;
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
        Long newsId = 1L;
        comment.setNewsId(newsId);
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

}

