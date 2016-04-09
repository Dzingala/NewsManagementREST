package by.epam.lab.task1.dao.impl;


import by.epam.lab.task1.dao.TagDAO;
import by.epam.lab.task1.model.Tag;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class JdbcTagDAO implements TagDAO{
    private final static Logger logger= Logger.getLogger(JdbcTagDAO.class);
    private final static String CREATE_TAG_QUERY="INSERT INTO DZINHALA.TAG(TAG_NAME) VALUES (?)";
    private final static String READ_TAG_QUERY="SELECT * FROM DZINHALA.TAG WHERE TAG_ID=?";
    private final static String UPDATE_TAG_QUERY="UPDATE DZINHALA.TAG SET TAG_NAME=? WHERE TAG_ID=?";
    private final static String DELETE_TAG_QUERY="DELETE FROM DZINHALA.TAG WHERE TAG_ID=?";
    @Autowired
    private DataSource dataSource;

    @Override
    public boolean createTag(Tag tag) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(CREATE_TAG_QUERY);
            ps.setString(1, tag.getTagName());
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
    public Tag readTagById(long tagId) {
        Connection conn = null;
        Tag tag=null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(READ_TAG_QUERY);
            ps.setLong(1, tagId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tag= new Tag();
                tag.setTagName(rs.getString("TAG_NAME"));
                tag.setTagId(tagId);
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
        return tag;
    }

    @Override
    public boolean updateTagById(Tag tag) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_TAG_QUERY);
            ps.setString(1, tag.getTagName());
            ps.setLong(2,tag.getTagId());
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
    public boolean deleteTagById(long tagId) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_TAG_QUERY);
            ps.setLong(1, tagId);
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
