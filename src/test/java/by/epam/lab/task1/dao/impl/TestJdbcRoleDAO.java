package by.epam.lab.task1.dao.impl;

import by.epam.lab.task1.entity.Role;
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
public class TestJdbcRoleDAO {
    private static final String tempName="tempname";
    @Autowired
    private JdbcRoleDAO roleDAO;

    @Test
    public void createTest() throws DAOException {
        Role role = new Role();
        role.setName(tempName);
        Long roleId = roleDAO.create(role);
    }

    @Test
    public void readTest() throws DAOException{
        Long id=roleDAO.create(new Role(3,tempName));
        Role role = roleDAO.read(id);
        assertTrue(role.getId()==id);
    }

    @Test
    public void updateTest() throws DAOException {
        Role role = new Role(1,tempName);
        role.setId(roleDAO.create(role));;
        roleDAO.update(role.getId(), role);
        Role roleExpected = roleDAO.read(role.getId());
        assertTrue(role.equals(roleExpected));
    }

    @Test
    public void deleteTest() throws DAOException {
        Long roleId = 1L;
        roleDAO.delete(roleId);
        assertNull(roleDAO.read(roleId));
    }

}