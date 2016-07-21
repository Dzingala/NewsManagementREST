package by.epam.lab.task.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Ivan_Dzinhala on 7/20/2016.
 */
public class HibernateCustomTemplate extends HibernateTemplate {

    @Autowired(required=true)
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        System.out.println("Setting SessionFactory");
        this.sessionFactory = sessionFactory;
        super.setSessionFactory(sessionFactory);
    }
    @Override
    public void afterPropertiesSet() {
        System.out.println("Checking if properties set..."+this.sessionFactory);
        setSessionFactory(sessionFactory);
        super.afterPropertiesSet();
    }
}
