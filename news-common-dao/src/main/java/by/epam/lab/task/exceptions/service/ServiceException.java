package by.epam.lab.task.exceptions.service;
/**
 * Exception which is thrown in Service layer.
 *
 * @author Ivan Dzinhala
 */
public class ServiceException extends Exception{
    public ServiceException(){}
    public ServiceException(String message, Throwable exception) {
        super(message, exception);
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable exception) {
        super(exception);
    }
}
