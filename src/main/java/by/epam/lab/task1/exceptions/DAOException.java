package by.epam.lab.task1.exceptions;

import java.sql.SQLException;


public class DAOException extends SQLException {
    public DAOException(){}
    public DAOException(String message){
        super(message);
    }
    public DAOException(InterruptedException e)  {
        super(e.fillInStackTrace());
    }
    public DAOException(SQLException e) {
        super(e.fillInStackTrace());
    }
}
