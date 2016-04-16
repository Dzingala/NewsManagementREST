package by.epam.lab.task1.repository;


import by.epam.lab.task1.entity.User;
import by.epam.lab.task1.exceptions.DAOException;
/**
 * @author Ivan Dzinhala
 * @see by.epam.lab.task1.repository.GenericRepository
 */
public interface UserRepository extends GenericRepository<User> {
    /**
     * Find id of user by it's login
     * @param login
     * @throws DAOException
     * @return user id
     */
    Long readIdByLogin(String login) throws DAOException;
}
