package com.epam.command;


import com.epam.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ivan Dzinhala
 */
public interface Command {
    /**
     * Perform business logic depends on nature of command
     * @param request
     * @return String the url of page to go after execution
     * @throws com.epam.exception.CommandException
     */
    String execute(HttpServletRequest request) throws CommandException;
}
