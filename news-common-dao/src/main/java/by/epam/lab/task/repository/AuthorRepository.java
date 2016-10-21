package by.epam.lab.task.repository;

import by.epam.lab.task.entity.Author;
import by.epam.lab.task.exceptions.dao.DAOException;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task.repository.GenericRepository
 */
public interface AuthorRepository extends GenericRepository<Author> {
    /**
     * Find author id that is connected with news.
     * @param newsId
     * @throws DAOException
     * @return author id
     */
    Long readAuthorIdByNewsId(Long newsId) throws DAOException;

}
