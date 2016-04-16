package by.epam.lab.task1.service;

import by.epam.lab.task1.entity.dto.UserTO;
import by.epam.lab.task1.exceptions.ServiceException;

/**
 * @author Ivan Dzinhala
 */
public interface UserService {
    /**
     * Registration user in the database.
     * Validate parameters.
     * @param userTO
     * @return UserTO
     * @throws ServiceException
     */
    UserTO registration(UserTO userTO) throws ServiceException;

    /**
     * Login in the system.
     * @param login
     * @param password
     * @return UserTO
     * @throws ServiceException
     */
    UserTO login(String login, String password) throws ServiceException;

    /**
     * Delete the user from the database.
     * @param userTO
     * @throws ServiceException
     */
    void delete(UserTO userTO) throws ServiceException;

    /**
     * Edit an information about user.
     * Validate parameters.
     * @param userTO
     * @throws ServiceException
     */
    void edit(UserTO userTO) throws ServiceException;
}
