package by.epam.lab.task.repository.impl;


import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.repository.RoleRepository;
import by.epam.lab.task.entity.Role;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    private final static Logger logger= Logger.getLogger(RoleRepositoryImpl.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Implementation of RoleRepository method create.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public Long create(Role role) throws DAOException {
        logger.debug("Creating role in RoleRepositoryImpl");

        Session session=null;
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
        }catch (Exception e) {
            logger.error("DAOException while creating role in RoleRepositoryImpl");
            logger.debug("Role was not created");
            throw new DAOException();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while creating a tag");
                }
            }
        }

        return null;
    }
    /**
     * Implementation of RoleRepository method read.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     * @see by.epam.lab.task.exceptions.dao.NoSuchEntityException
     */
    @Override
    public Role read(Long roleId) throws DAOException {
        logger.debug("Reading role in RoleRepositoryImpl");

        Session session = null;
        Role role = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            role = (Role) session.load(Role.class, roleId);
        } catch (Exception e) {
            logger.error("DAOException while reading role in RoleRepositoryImpl");
            logger.debug("Role was not read");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    logger.error("HibernateException while reading a role");
                }
            }
        }
        if(role==null){
            logger.debug("Here is no role with id="+roleId);
            logger.error("NoSuchEntity while reading role in RoleRepositoryImpl");
            throw new NoSuchEntityException();
        }
        return role;
    }
    /**
     * Implementation of RoleRepository method update.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void update(Long id, Role role) throws DAOException {
        logger.debug("Updating role in RoleRepositoryImpl");

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            role.setId(id);
            session.getTransaction().begin();
            session.update(role);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while updating role in RoleRepositoryImpl");
            logger.debug("Role was not updated");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while closing connection");
                }
            }
        }
    }
    /**
     * Implementation of RoleRepository method delete.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public void delete(Long id) throws DAOException {
        logger.debug("Deleting role in RoleRepositoryImpl");
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Role toDeleteTag = (Role)session.load(Role.class,id);
            session.delete(toDeleteTag);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.error("DAOException while deleting role in RoleRepositoryImpl");
            logger.debug("Role was not deleted");
            throw new DAOException(e);
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("HibernateException while deleting a role");
                }
            }
        }
    }
    /**
     * Implementation of RoleRepository method readAll.
     * @see by.epam.lab.task.exceptions.dao.DAOException
     */
    @Override
    public List<Role> readAll() throws DAOException {
        logger.debug("Reading all roles in RoleRepositoryImpl");
        Session session= null;
        List<Role> roles=new ArrayList<>();
        try{
            session=HibernateUtil.getSessionFactory().openSession();
            roles=session.createCriteria(Role.class).list();
        }catch (Exception e){
            logger.error("DAOException while reading all roles in RoleRepositoryImpl");
            logger.debug("Authors was not read");
            throw new DAOException(e);
        }finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                }catch (HibernateException e){
                    logger.error("Hibernate exception while reading all roles");
                }
            }
        }
        return roles;
    }

}
