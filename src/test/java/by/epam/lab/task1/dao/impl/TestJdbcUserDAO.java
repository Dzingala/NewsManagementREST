package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.entity.User;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestJdbcUserDAO {
    private static final String tempName="tempname";
    private static final String tempLogin="templogin";
    private static final String tempPass="temppass";
    private static final String tempLogin1="tempname1";
    @Autowired
    private JdbcUserDAO userDAO;

    @Test
    public void createTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        Long userId = userDAO.create(user);
    }

    @Test
    public void readTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        Long userId = userDAO.create(user);
        user.setId(userId);
        User newUser = userDAO.read(userId);
        assertTrue(newUser.equals(user));
    }

    @Test
    public void updateTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        Long userId = userDAO.create(user);
        user.setId(userId);
        String templog1=tempLogin1;
        user.setLogin(templog1);
        userDAO.update(userId, user);
        User userExpected = userDAO.read(userId);
        assertTrue( user.equals(userExpected));

    }

    @Test
    public void deleteTest() throws DAOException {
        Long userId = 1L;
        userDAO.delete(userId);
        assertNull(userDAO.read(userId));
    }

    @Test
    public void readIdByLogin() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        Long userId=userDAO.create(user);
        Long newUserId = userDAO.readIdByLogin(tempLogin);
        assertTrue(userId.longValue()==newUserId.longValue());
    }
}
