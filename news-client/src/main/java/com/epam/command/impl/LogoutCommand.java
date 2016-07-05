package com.epam.command.impl;

import com.epam.command.Command;
import com.epam.exception.CommandException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component("logout")
public class LogoutCommand implements Command{
    private static final String PATH_PAGE_INDEX="/index.jsp";
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        invalidateSession(request.getSession(true));
        request.getSession().invalidate();

        return PATH_PAGE_INDEX;
    }
    /*This should remove from the session all beans whose name ends in "Bean"
    and all the other attribute in the extraAttr array.*/
    private static void invalidateSession(HttpSession session) {
        String[] extraAttrs = { "loggedUserInfo", };
        java.util.Enumeration attrs = session.getAttributeNames();
        while (attrs.hasMoreElements()) {
            String attr = (String)attrs.nextElement();
            if (attr.endsWith("Bean")) {
                session.removeAttribute(attr);
            }
        }
        for (String extraAttr : extraAttrs) session.removeAttribute(extraAttr);
    }
}
