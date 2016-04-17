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

    /*@Test
    public void registrationTest() throws DAOException, ServiceException {
        User user = new User();
        Role role = new Role();
        String login = user.getLogin();
        UserTO userTO = new UserTO(user, role);

        Mockito.when(userRepository.findId(login)).thenThrow(NoSuchEntityException.class);
        Mockito.when(userRepository.create(user)).thenReturn(user.getId());
        Mockito.when(roleService.add(role)).thenReturn(role.getRoleId());

        userService.registration(userTO);

        Mockito.verify(userRepository).findId(login);
        Mockito.verify(userRepository).create(user);
        Mockito.verify(roleService).add(role);
    }*/

    @Test
    public void loginTest() throws ServiceException, DAOException {

        User user = new User(1L,1L, testName, testName, testName);
        Role role = new Role(1L,testName);

        Mockito.when(userRepository.readIdByLogin(user.getLogin())).thenReturn(user.getId());
        Mockito.when(userRepository.read(user.getId())).thenReturn(user);
        //Mockito.when(roleService.findIdByUserId(user.getId())).thenReturn(role.getRoleId());
        //Mockito.when(userRepository.read(user.getId()).getRoleId()).thenReturn(role.getId());
        Mockito.when(roleService.readById(role.getId())).thenReturn(role);

        userService.login(user.getLogin(), user.getPassword());

        Mockito.verify(userRepository).readIdByLogin(user.getLogin());
        Mockito.verify(userRepository).read(user.getId());

        //Mockito.verify(roleService).findIdByUserId(user.getId());
        //Mockito.verify(userRepository.read(user.getId()).getRoleId());
        Mockito.verify(roleService).readById(role.getId());
    }

    @Test
    public void deleteTestWithUserId() throws ServiceException, DAOException {
        User user = new User(1L,1L, testName, testName, testName);
        Role role = new Role(1L, testName);
        UserTO userTO = new UserTO(user, role);

        userService.delete(userTO);

        Mockito.verify(userRepository).delete(user.getId());
        Mockito.verify(roleService).delete(role);
    }

    @Test
    public void updateTestWithException() throws ServiceException, DAOException {
        User user = new User(1L,1L,testName, testName, testName);
        Role role = new Role(1L, testName);
        UserTO userTO = new UserTO(user, role);

        userService.edit(userTO);

        Mockito.verify(userRepository).update(user.getId(), user);
        Mockito.verify(roleService).update(role);
    }
}
