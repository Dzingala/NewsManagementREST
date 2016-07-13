package by.epam.lab.task.repository;

import by.epam.lab.task.exceptions.dao.DAOException;

import java.util.List;

/**
 * @author Ivan Dzinhala
 */
public interface GenericRepository<Entity> {

    /**
     * Create row in table with required parameters.
     * @param entity
     * @return the id of row in table, null will never be returned
     * @throws DAOException
     */
    Long create(Entity entity) throws DAOException;

    /**
     * Read the row from table by key.
     * @param id
     * @return the entity that has been found
     * @throws DAOException
     */
    Entity read(Long id) throws DAOException;

    /**
     * Update the row in table by key, replace values of column by the same values from entity.
     * @param id
     * @param entity
     * @return
     * @throws DAOException
     */
    void update(Long id, Entity entity) throws DAOException;

    /**
     * Delete the row from table by key.
     * @param id
     * @return
     * @throws DAOException
     */
    void delete(Long id) throws DAOException;

    /**
     * Read all rows from table.
     * @return the list of entities
     * @throws DAOException
     */
    List<Entity> readAll() throws DAOException;
}
