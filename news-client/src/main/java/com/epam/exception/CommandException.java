package com.epam.exception;

/**
 * @author Ivan Dzinhala
 */
public class CommandException extends Exception {
    public CommandException(){}

    /**
     *
     * @param message
     * @param exception
     */
    public CommandException(String message, Throwable exception) {
        super(message, exception);
    }

    /**
     *
     * @param message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     *
     * @param exception
     */
    public CommandException(Throwable exception) {
        super(exception);
    }
}
