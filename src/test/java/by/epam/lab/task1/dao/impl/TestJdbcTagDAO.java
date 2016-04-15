package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.entity.Tag;
import by.epam.lab.task1.exceptions.DAOException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestJdbcTagDAO {
    private static final String tempName="tempname";
    @Autowired
    private JdbcTagDAO tagDAO;

    @Test
    public void createTest() throws DAOException {
        Tag tag = new Tag();
        tag.setName(tempName);
        Long tagId = tagDAO.create(tag);
    }
    @Test
    public void readTest() throws DAOException {
        Tag tag = tagDAO.read(1L);
        assertNotNull(tag);
    }
    @Test
    public void updateTest() throws DAOException {
        Long tagId = 1L;
        Tag tag = tagDAO.read(tagId);
        tag.setName(tempName);
        tagDAO.update(tagId, tag);
        Tag tagExpected = tagDAO.read(tagId);
        assertTrue(tag.equals(tagExpected));
    }

    @Test
    public void deleteTest() throws DAOException {
        Long tagId=tagDAO.create(new Tag(1,tempName));
        tagDAO.delete(tagId);
        assertNull(tagDAO.read(tagId));
    }

    @Test
    public void readTagsIdByNewsIdTest() throws DAOException {
        Long newsId = 1L;
        ArrayList<Long> tagsId = tagDAO.readTagsIdByNewsId(newsId);
        assertNotNull(tagsId);

    }
    @Test
    public void readAllTest() throws DAOException{
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName(tempName);
        Long tagId=tagDAO.create(tag);
        ArrayList<Tag> tags=tagDAO.readAll();
        assertFalse(tags.isEmpty());
    }


}
