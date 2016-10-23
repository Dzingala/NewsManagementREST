package by.epam.lab.task.repository.impl;

import by.epam.lab.task.entity.User;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
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
import java.util.List;

import static org.junit.Assert.*;
/**
 * @author Ivan Dzinhala
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringDatasourceTest.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup(value = "classpath:dataset.xml")
@DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
public class TestUserRepository {
    private static final String tempName="tempname";
    private static final String tempLogin="templogin";
    private static final String tempPass="temppass";
    private static final String tempLogin1="tempname1";
    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void createTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        user.setRoleId(1L);
        Long userId = userRepository.create(user);
    }

    @Test
    public void readTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        user.setRoleId(1L);
        Long userId = userRepository.create(user);
        user.setId(userId);
        User newUser = userRepository.read(userId);
        assertTrue(!newUser.equals(user));
    }

    @Test
    public void updateTest() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        user.setRoleId(1L);
        Long userId = userRepository.create(user);
        user.setId(userId);
        String templog1=tempLogin1;
        user.setLogin(templog1);
        userRepository.update(userId, user);
        User userExpected = userRepository.read(userId);
        System.out.println(user);
        System.out.println(userExpected);
        assertTrue( !user.equals(userExpected));

    }

    @Test(expected = DAOException.class)
    public void deleteTest() throws DAOException {
        Long userId = 1L;
        userRepository.delete(userId);
        assertNull(userRepository.read(userId));
    }

    @Test
    public void readIdByLogin() throws DAOException {
        User user = new User();
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        user.setRoleId(1L);
        Long userId= userRepository.create(user);
        Long newUserId = userRepository.readIdByLogin(tempLogin);
        assertTrue(userId.longValue()==newUserId.longValue());
    }
    @Test
    public void readAllTest() throws DAOException{
        User user = new User();
        user.setId(1L);
        user.setName(tempName);
        user.setLogin(tempLogin);
        user.setPassword(tempPass);
        user.setRoleId(1L);

        Long userId= userRepository.create(user);
        List<User> users= userRepository.readAll();
        assertFalse(users.isEmpty());
    }

}