package by.epam.lab.task1.dao;

import by.epam.lab.task1.exceptions.DAOException;

import java.io.Serializable;
import java.util.ArrayList;

public interface GenericDAO<Entity> {
    Long create(Entity entity) throws DAOException;
    Entity read(Long id) throws DAOException;
    void update(Long id, Entity entity) throws DAOException;
    void delete(Long id) throws DAOException;
}
