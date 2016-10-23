package by.epam.lab.task.repository;


import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task.repository.GenericRepository
 */
public interface UserRepository extends GenericRepository<User> {
    /**
     * Find id of user by it's login.
     * @param login
     * @throws DAOException
     * @return user id
     */
    Long readIdByLogin(String login) throws DAOException;
}
