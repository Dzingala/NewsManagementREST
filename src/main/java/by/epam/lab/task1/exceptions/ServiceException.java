package by.epam.lab.task1.exceptions;

public class ServiceException extends Exception{
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable exception) {
        super(exception);
    }

    public ServiceException(String message, Throwable exception) {
        super(message, exception);
    }
}
