package by.epam.lab.task.utils.hibernate;


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This util-class provides a possibility to access database
 * with the help of Hibernate.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger logger= Logger.getLogger(HibernateUtil.class);

    private HibernateUtil(){
        throw new IllegalAccessError("Utility class");
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            // Make sure you log the exception, as it might be swallowed
            logger.error("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Because: this method is used for shutting the session factory down.
     */
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}