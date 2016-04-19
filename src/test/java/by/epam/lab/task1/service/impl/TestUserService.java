package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Role;
import by.epam.lab.task1.entity.User;
import by.epam.lab.task1.entity.dto.UserTO;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Ivan Dzinhala
 */
public class TestUserService {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleServiceImpl roleService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private UserServiceImpl userService;

    private static final String testName="test";

    @Test
    public void registrationTest() throws DAOException, ServiceException {
        Role role = new Role(1l,testName);
        User user = new User();
        user.setLogin(testName);
        user.setPassword(testName);
        user.setName(testName);
        Long roleId = roleService.create(role);
        user.setRoleId(roleId);
        Mockito.when(userRepository.readIdByLogin(user.getLogin())).thenThrow(NoSuchEntityException.class);
        Mockito.when(roleService.create(role)).thenReturn(role.getId());
        Mockito.when(userRepository.create(user)).thenReturn(user.getId());
        UserTO userTO=new UserTO(user,role);
        userService.registration(userTO);
        Mockito.verify(userRepository).readIdByLogin(user.getLogin());
        Mockito.verify(userRepository).create(user);
        Mockito.verify(roleService).create(role);
    }

    @Test
    public void loginTest() throws ServiceException, DAOException {
        Role role = new Role(1L,testName);
        User user = new User(role.getId(),1L, testName, testName, testName);
        Mockito.when(userRepository.readIdByLogin(user.getLogin())).thenReturn(user.getId());
        Mockito.when(userRepository.read(user.getId())).thenReturn(user);
        Mockito.when(roleService.readById(role.getId())).thenReturn(role);
        userService.login(user.getLogin(), user.getPassword());
        Mockito.verify(userRepository).readIdByLogin(user.getLogin());
        Mockito.verify(userRepository).read(user.getId());
        Mockito.verify(roleService).readById(role.getId());

    }

    @Test
    public void deleteTest() throws ServiceException, DAOException {
        UserTO userTO = new UserTO();
        userTO.setUser(new User());
        userService.delete(userTO);
        Mockito.verify(userRepository).delete(userTO.getUser().getId());
    }

    @Test
    public void updateTest() throws ServiceException, DAOException {
        Role role = new Role(1L, testName);
        User user = new User(role.getId(),1L,testName, testName, testName);
        UserTO userTO = new UserTO(user, role);
        userService.edit(userTO);
        Mockito.verify(userRepository).update(userTO.getUser().getId(), userTO.getUser());

    }
}
