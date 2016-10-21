package by.epam.lab.task.service;


import by.epam.lab.task.entity.Role;
import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.exceptions.service.ServiceException;

/**
 * @author Ivan Dzinhala
 */
public interface RolesService {

    /**
     * Add role to database.
     * @param role
     * @throws ServiceException
     * @return id of row that has been added
     */
    Long create(Role role) throws ServiceException;

    /**
     * Get role by it's id, if it exists.
     * @param roleId
     * @throws ServiceException
     * @return required role
     */
    Role readById(Long roleId) throws ServiceException, NoSuchEntityException;

    /**
     * Edit role.
     * @param role
     * @throws ServiceException
     */
    void update(Role role) throws ServiceException;

    /**
     * Delete role.
     * @param role
     * @throws ServiceException
     */
    void delete(Role role) throws ServiceException;

}
