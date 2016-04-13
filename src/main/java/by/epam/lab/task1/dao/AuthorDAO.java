package by.epam.lab.task1.dao;

import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;

public interface AuthorDAO extends GenericDAO<Author>{
    Long readAuthorIdByNewsId(Long newsId) throws DAOException;

}
