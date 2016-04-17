package by.epam.lab.task1.service;

import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.exceptions.service.ServiceException;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 */
public interface CommentService {

    /**
     * Add comment to database.
     * @param comment
     * @throws ServiceException
     * @return comment id
     */
    Long create (Comment comment) throws ServiceException;

    /**
     * Get all comments for that news.
     * @param newsId
     * @throws ServiceException
     * @return list of comments
     */
    ArrayList<Comment> readAllByNewsId(Long newsId) throws ServiceException;

    /**
     * Delete comment from database.
     * @param comment
     * @throws ServiceException
     */
    void delete(Comment comment) throws ServiceException;

    /**
     * Update comment content.
     * @param comment
     * @throws ServiceException
     */
    void update(Comment comment) throws ServiceException;

    /**
     * Get comment by id.
     * @param commentId
     * @throws ServiceException
     */
    Comment read(Long commentId)throws ServiceException;
}
