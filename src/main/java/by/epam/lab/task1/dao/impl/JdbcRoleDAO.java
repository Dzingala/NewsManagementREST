package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.RoleDAO;
import by.epam.lab.task1.model.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class JdbcRoleDAO implements RoleDAO {
    private final static Logger logger= Logger.getLogger(JdbcRoleDAO.class);
    private final static String CREATE_ROLE_QUERY="INSERT INTO DZINHALA.ROLES(ROLE_NAME) VALUES (?)";
    private final static String READ_ROLE_QUERY="SELECT * FROM DZINHALA.ROLES WHERE ROLE_ID=?";
    private final static String UPDATE_ROLE_QUERY="UPDATE DZINHALA.ROLES SET ROLE_NAME=? WHERE ROLE_ID=?";
    private final static String DELETE_ROLE_QUERY="DELETE FROM DZINHALA.ROLES WHERE ROLE_ID=?";

    @Autowired
    private DataSource dataSource;

    @Override
    public boolean createRole(Role role) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_ROLE_QUERY);
            ps.setString(1, role.getRoleName());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public Role readRoleById(long roleId) {
        Connection conn = null;
        Role role = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_ROLE_QUERY);
            ps.setLong(1, roleId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                role = new Role();
                role.setRoleName(rs.getString("ROLE_NAME"));
                role.setRoleId(roleId);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return role;
    }

    @Override
    public boolean updateRoleById(Role role) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_ROLE_QUERY);
            ps.setString(1, role.getRoleName());
            ps.setLong(2,role.getRoleId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

    @Override
    public boolean deleteRoleById(long roleId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_ROLE_QUERY);
            ps.setLong(1, roleId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return true;
    }

}
