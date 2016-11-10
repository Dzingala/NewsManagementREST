package by.epam.lab.task.repository.impl;

import by.epam.lab.task.entity.Role;
import by.epam.lab.task.exceptions.dao.DAOException;
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
public class TestRoleRepository {
    private static final String tempName="tempname";
    @Autowired
    private RoleRepositoryImpl roleDAO;

    @Test
    public void createTest() throws DAOException {
        Role role = new Role();
        role.setName(tempName);
        assertNotNull(roleDAO.create(role));
    }

    @Test
    public void readTest() throws DAOException{
        assertNotNull(roleDAO.read(1L));
    }

    @Test
    public void updateTest() throws DAOException {
        Long roleId=1L;
        Role role= roleDAO.read(roleId);
        role.setName(tempName);
        roleDAO.update(roleId,role);
        Role expectedRole=roleDAO.read(roleId);
        assertTrue(role.equals(expectedRole));
    }

    @Test(expected = DAOException.class)
    public void deleteTest() throws DAOException {
        Long roleId = 1L;
        roleDAO.delete(roleId);
        assertNull(roleDAO.read(roleId));
    }

    @Test
    public void readAllTest() throws DAOException{
        List<Role> newsList = roleDAO.readAll();
        assertNotNull(newsList);
    }
}