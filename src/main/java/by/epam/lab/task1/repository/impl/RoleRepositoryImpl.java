package by.epam.lab.task1.repository.impl;


import by.epam.lab.task1.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task1.repository.RoleRepository;
import by.epam.lab.task1.entity.Role;
import by.epam.lab.task1.exceptions.dao.DAOException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    private final static Logger logger= Logger.getLogger(RoleRepositoryImpl.class);
    private static final String CREATE_ROLE_QUERY = " INSERT INTO DZINHALA.ROLES (ROLE_NAME) VALUES (?) ";
    private static final String READ_ROLE_QUERY = " SELECT ROLE_ID, ROLE_NAME FROM DZINHALA.ROLES WHERE ROLE_ID = ? ";
    private static final String UPDATE_ROLE_QUERY = " UPDATE DZINHALA.ROLES SET ROLE_NAME = ? WHERE ROLE_ID = ? ";
    private static final String DELETE_ROLE_QUERY = " DELETE FROM DZINHALA.ROLES  WHERE ROLE_ID = ? ";
    private static final String READ_ALL_ROLES_QUERY=" SELECT ROLE_NAME,ROLE_ID FROM DZINHALA.ROLES";

    private static final String COLUMN_NAME_ROLE_NAME = "ROLE_NAME";
    private static final String COLUMN_NAME_ROLE_ID = "ROLE_ID";


    @Autowired
    private DataSource dataSource;


    public Long create(Role role) throws DAOException {
        logger.debug("Creating role in RoleRepositoryImpl");
        Connection conn=null;
        Long roleId=null;
        String[] keys = {COLUMN_NAME_ROLE_ID};
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(CREATE_ROLE_QUERY, keys)) {
                ps.setString(1, role.getName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    rs.next();
                    roleId = rs.getLong(1);
                    logger.debug("Role id="+roleId+" was created");
                }
            }finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e){
            logger.error("DAOException while creating role in RoleRepositoryImpl");
            logger.debug("Role was not created");
            throw new DAOException();
        }
        return roleId;
    }

    public Role read(Long roleId) throws DAOException {
        logger.debug("Reading role in RoleRepositoryImpl");
        Connection conn=null;
        Role role = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ROLE_QUERY)){
                ps.setLong(1, roleId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        role = new Role(roleId,
                                rs.getString(COLUMN_NAME_ROLE_NAME)
                        );
                    }
                    else{
                        logger.debug("Role with id="+roleId+" does not exist");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading role in RoleRepositoryImpl");
            logger.debug("Role was not read");
            throw new DAOException(e);
        }
        if(role==null){
            logger.debug("Here is no role with id="+roleId);
            logger.error("NoSuchEntity while reading role in RoleRepositoryImpl");
            throw new NoSuchEntityException();
        }
        return role;
    }

    public void update(Long id, Role role) throws DAOException {
        logger.debug("Updating role in RoleRepositoryImpl");
        Connection conn =null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(UPDATE_ROLE_QUERY)) {
                ps.setString(1, role.getName());
                ps.setLong(2, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while updating role in RoleRepositoryImpl");
            logger.debug("Role was not updated");
            throw new DAOException(e);
        }
    }

    public void delete(Long id) throws DAOException {
        logger.debug("Deleting role in RoleRepositoryImpl");
        Connection conn=null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(DELETE_ROLE_QUERY)) {
                ps.setLong(1, id);
                ps.executeUpdate();
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while deleting role in RoleRepositoryImpl");
            logger.debug("Role was not deleted");
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Role> readAll() throws DAOException {
        logger.debug("Reading all roles in RoleRepositoryImpl");
        Connection conn=null;
        ArrayList<Role> roles = null;
        try {
            conn = dataSource.getConnection();
            try (PreparedStatement ps = conn.prepareStatement(READ_ALL_ROLES_QUERY)){
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        roles=new ArrayList<>();
                        roles.add(new Role(rs.getLong(COLUMN_NAME_ROLE_ID),
                                rs.getString(COLUMN_NAME_ROLE_NAME)
                        ));
                        while (rs.next()){
                            roles.add(new Role(rs.getLong(COLUMN_NAME_ROLE_ID),
                                    rs.getString(COLUMN_NAME_ROLE_NAME)
                            ));
                        }
                    }
                    else{
                        logger.debug("There are no roles in database");
                    }
                }
            } finally {
                DataSourceUtils.releaseConnection(conn, dataSource);
            }
        }catch (SQLException e) {
            logger.error("DAOException while reading role in RoleRepositoryImpl");
            logger.debug("Roles was not read");
            throw new DAOException(e);
        }
        return roles;
    }

}
