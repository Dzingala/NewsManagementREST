package by.epam.lab.task.exceptions.dao;

import java.sql.SQLException;

/**
 * Exception which is thrown in Repository layer.
 *
 * @author Ivan Dzinhala
 */
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
