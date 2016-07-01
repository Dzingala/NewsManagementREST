package com.epam.controller;


import com.epam.command.Command;
import com.epam.exception.CommandException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    private static final String COMMAND_KEY = "command";


    private static final String LOGIN_PAGE="/WEB-INF/jsp/login_page.jsp";

    private WebApplicationContext context;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    public Controller() {
        super();
    }

    /**
     * Work with get request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = doProcess(request);
        request.getRequestDispatcher(page).forward(request, response);
    }

    /**
     * Work with post request
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = doProcess(request);
        request.getRequestDispatcher(page).forward(request, response);
    }

    /**
     * Receive a request, define command to execute
     * @param request
     * @return String the url of page to go after execution
     */
    private String doProcess(HttpServletRequest request){
        String page = null;
        HttpSession session = request.getSession();
        try {
            if(session.isNew()){
                return LOGIN_PAGE;
            }
            String commandName = request.getParameter(COMMAND_KEY);
            System.out.println("Command:"+commandName);
            Command command = (Command) context.getBean(commandName);
            System.out.println(command);
            page = command.execute(request);
            System.out.println(page);
        } catch (CommandException e) {
            LOG.error("Error in controller: " + e.getMessage());
            request.setAttribute(PARAMETER_ERROR, e.getMessage());
            page = request.getParameter(PARAMETER_PAGE);
        } catch (Exception e){
            LOG.error("Error in controller(exception): " + e.getMessage());
            page = ERROR_PAGE;
        }
        return page;
    }
}
