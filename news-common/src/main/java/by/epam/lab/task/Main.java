package by.epam.lab.task;

import by.epam.lab.task.entity.User;
import by.epam.lab.task.util.HibernateUtil;
import org.hibernate.Session;


public class Main {
    public static void main(String ... args){
        System.out.println("Maven + Hibernate Annotation + Oracle");
        System.out.println("tut0");
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("tut1");
        session.beginTransaction();
        User user = new User();
        System.out.println("tut2");
        user.setId(21L);
        user.setLogin("logintemp");
        user.setRoleId(1L);
        user.setPassword("pass");
        user.setName("nametemp");

        session.save(user);
        System.out.println("tut3");
        session.getTransaction().commit();
        System.out.println("tut4");
    }
}
