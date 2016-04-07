package by.epam.lab.task1.exceptions;


import java.sql.SQLException;

public class LogicException extends DAOException{
    public LogicException(){}
    public LogicException(String message){
        super(message);
    }
    public LogicException(InterruptedException e)  {
        super(e.getMessage());
    }
    public LogicException(SQLException e) {
        super(e.getMessage());
    }
}
