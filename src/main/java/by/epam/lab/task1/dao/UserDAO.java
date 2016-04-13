package by.epam.lab.task1.dao;


import by.epam.lab.task1.entity.User;
import by.epam.lab.task1.exceptions.DAOException;

public interface UserDAO extends GenericDAO<User>{
    Long readIdByLogin(String login) throws DAOException;
}
