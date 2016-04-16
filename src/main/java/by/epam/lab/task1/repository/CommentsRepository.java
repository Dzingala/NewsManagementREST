package by.epam.lab.task1.repository;


import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task1.repository.GenericRepository
 */
public interface CommentsRepository extends GenericRepository<Comment> {
    /**
     * Find all comments id that are connected with news.
     * @param newsId
     * @throws DAOException
     * @return list of comments id
     */
    ArrayList<Long> readCommentsIdByNewsId(Long newsId) throws DAOException;
}
