package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Role;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Ivan Dzinhala
 */
public class TestRoleService {
    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private RoleServiceImpl roleService;

    @Test
    public void addTest() throws DAOException, ServiceException {

        Role role = new Role();
        roleService.create(role);
        Mockito.verify(roleRepository).create(role);
    }

    @Test
    public void editTest() throws DAOException, ServiceException {

        Role role = new Role();
        roleService.update(role);
        Mockito.verify(roleRepository).update(role.getId(), role);
    }

    @Test
    public void deleteTest() throws DAOException, ServiceException {

        Role role = new Role();
        roleService.delete(role);
        Mockito.verify(roleRepository).delete(role.getId());
    }

    @Test
    public void getTest() throws DAOException, ServiceException {
        Role role = new Role();
        roleService.readById(role.getId());
        Mockito.verify(roleRepository).read(role.getId());
    }


}
