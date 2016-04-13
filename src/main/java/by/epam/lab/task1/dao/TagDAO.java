package by.epam.lab.task1.dao;


import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.exceptions.DAOException;

import java.util.ArrayList;

public interface TagDAO extends GenericDAO<Tag>{
    ArrayList<Long> readTagsIdByNewsId(Long newsId) throws DAOException;
}
