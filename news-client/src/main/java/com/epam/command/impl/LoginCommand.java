package com.epam.command.impl;

import by.epam.lab.task.entity.dto.UserTO;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.service.UserService;
import com.epam.command.Command;
import com.epam.exception.CommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component("login")
public class LoginCommand implements Command {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Autowired
    UserService userService;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        UserTO userTO=null;
        try{
            userTO=userService.login(login,pass);
            request.getSession().setAttribute("login",login);
            request.getSession().setAttribute("pass",pass);
            System.out.println(userTO);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return "/WEB-INF/jsp/redirection.jsp";
    }
}
