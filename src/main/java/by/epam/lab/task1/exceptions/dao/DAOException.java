package by.epam.lab.task1.exceptions.dao;

import java.sql.SQLException;


public class DAOException extends SQLException {
    public DAOException(){}
    public DAOException(String message, Throwable exception) {
        super(message, exception);
    }
    public DAOException(String message) {
        super(message);
    }
    public DAOException(Throwable exception) {
        super(exception);
    }
}
