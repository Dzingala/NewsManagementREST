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

    /**
     * Set existing role's id to existing user by id.
     * @param userId
     * @param roleId
     * @throws DAOException
     */
    void setRoleIdById(Long userId,Long roleId) throws DAOException;
}
