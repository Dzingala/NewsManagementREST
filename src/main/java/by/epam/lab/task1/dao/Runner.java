package by.epam.lab.task1.dao;

import by.epam.lab.task1.dao.impl.JdbcAuthorDAO;
import by.epam.lab.task1.dao.impl.JdbcRoleDAO;
import by.epam.lab.task1.dao.impl.JdbcUserDAO;
import by.epam.lab.task1.model.Author;
import by.epam.lab.task1.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.sql.Timestamp;

public class Runner {
    public static void main(String args[]){
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringModule.xml");

        JdbcAuthorDAO userDAO = (JdbcAuthorDAO)context.getBean(JdbcAuthorDAO.class);

        Author author = userDAO.readAuthorById(2);

        //userDAO.insertUser(new User("name","login","password"));
    }
}
