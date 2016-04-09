package by.epam.lab.task1.dao;

import by.epam.lab.task1.model.Author;

public interface AuthorDAO {
    public boolean createAuthor(Author author);
    public Author readAuthorById(long roleId);
    public boolean updateAuthorById(Author author);
    public boolean deleteAuthorById(long roleId);
}
