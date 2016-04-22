package by.epam.lab.task1.exceptions.dao;


/**
 * Exception which is thrown in Repository layer while the entity was not found in database.
 *
 * @author Ivan Dzinhala
 */
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
