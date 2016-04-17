package by.epam.lab.task1.exceptions.dao;



public class NoSuchEntityException extends DAOException {
    public NoSuchEntityException(){}
    public NoSuchEntityException(String message, Throwable exception) {
        super(message, exception);
    }
    public NoSuchEntityException(String message) {
        super(message);
    }
    public NoSuchEntityException(Throwable exception) {
        super(exception);
    }
}
