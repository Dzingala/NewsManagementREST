package by.epam.lab.task.service.impl;


import by.epam.lab.task.entity.Role;
import by.epam.lab.task.entity.User;
import by.epam.lab.task.entity.dto.UserTO;
import by.epam.lab.task.exceptions.dao.DAOException;
import by.epam.lab.task.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task.exceptions.service.ServiceException;
import by.epam.lab.task.repository.UserRepository;
import by.epam.lab.task.service.RolesService;
import by.epam.lab.task.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ivan Dzinhala
 * @see UserService
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    private final static Logger logger= Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final RolesService roleService;

    @Autowired
    public UserServiceImpl(RolesService roleService, UserRepository userRepository) {
        this.roleService = roleService;
        this.userRepository = userRepository;
    }

    /**
     * Method checks if the role exists.
     * If the role doesn't exist, both Repository and Service layers will throw NoSuchEntityException.
     * That is why we decide what to return due to the fact of either exception was thrown or not
     * @param role
     * @return true if role does not exists and not null
     */
    private boolean checkRole(Role role) {
        logger.debug("Checking if role exists in UserService");
        if (role.getId() != null) {
            try {
                roleService.readById(role.getId());
            }catch (NoSuchEntityException e){
                logger.debug("Role does not exist and not null, creating...");
                return true;
            }catch (ServiceException e) {
                logger.error("ServiceException with existing role in UserService");
                return false;
            }
        }else{
            return false;//if role == null then there is no need to create
        }
        return false;//if role exists then there is no need to create role
    }
    /**
     * Implementation of UserService method registration.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserTO registration(UserTO userTO) throws ServiceException {
        logger.debug("Registering user in UserService");
        User user = userTO.getUser();
        String login = user.getLogin();
        try {
            if( checkUser(login) ){//true if login is free
                Role role = userTO.getRole();
                Long roleId=role.getId();
                if(checkRole(role)) {//true if role.roleId is not null and not exists ( means that we need to create it);
                    logger.debug("Role does not exist, creating..");
                    roleId = roleService.create(role);
                    role.setId(roleId);
                }
                else {
                    logger.debug("Role exists with id="+roleId);
                }
                user.setRoleId(roleId);
                Long userId = userRepository.create(user);
                userRepository.setRoleIdById(userId, roleId);
                user.setId(userId);
                userTO.setRole(role);
            }
            else {
                logger.debug("Login "+login+" already exists");
                throw new ServiceException("Login: " + login + " already exists");
            }
        } catch (DAOException e) {
            logger.error("DAOException while registering user in UserService");
            throw new ServiceException("ServiceException while registering user",e);
        }
        userTO.setUser(user);
        return userTO;
    }
    /**
     * Implementation of UserService method login.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserTO login (String login, String password) throws ServiceException {
        logger.debug("Authentication of user in UserService");
        UserTO userTO = null;
        try {
            Long userId = userRepository.readIdByLogin(login);
            User expectedUser = userRepository.read(userId);
            if (password.equals(expectedUser.getPassword())) {
                Long roleId = expectedUser.getRoleId();
                Role userRole = roleService.readById(roleId);
                userTO = new UserTO(expectedUser, userRole);
            } else {
                logger.debug("Incorrect password: "+password);
                throw new ServiceException("Password: " + password + " is not correct");
            }

        } catch (NoSuchEntityException e){
            throw new ServiceException("The user " + login + " wasn't registered", e);
        } catch (DAOException e) {
            logger.error("DAOException while authenticating user in UserService");
            throw new ServiceException("ServiceException while authenticating user",e);
        }

        return userTO;
    }

    /**
     * Implementation of UserService method delete.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete (UserTO userTO) throws ServiceException {
        logger.debug("Deleting user in UserService");
        User user = userTO.getUser();
        try {
            userRepository.delete(user.getId());
        } catch (DAOException e) {
            logger.error("DAOException while deleting user in UserService");
            throw new ServiceException("ServiceException while deleting user",e);
        }
    }
    /**
     * Implementation of UserService method edit.
     * @see by.epam.lab.task.exceptions.service.ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit (UserTO userTO) throws ServiceException {
        logger.debug("Updating user in UserService");
        User user = userTO.getUser();
        Role role = userTO.getRole();
        try {
            if(checkRole(role)){//if role!=null and doesn't exist in database, then it will be created
                roleService.create(role);
            }
            userRepository.update(user.getId(), user);
        } catch (DAOException e) {
            logger.error("DAOException while editing user in UserService");
            throw new ServiceException("ServiceException while editing user",e);
        }
    }

    /**
     * Method checks the user in the database by his login.
     * If the user doesn't exist, Repository layer will throw NoSuchEntityException.
     * That is why we decide what to return due to the fact of either exception was thrown or not
     * @param login
     * @return true if login is free, false if login is busy
     * @throws DAOException
     */
    private boolean checkUser (String login) throws DAOException {
        logger.debug("Checking if user exists in UserService");
        try {
            userRepository.readIdByLogin(login);
        } catch (NoSuchEntityException e) {
            logger.debug("User with login="+login+" does not exist");
            return true;
        }
        return false;
    }
}
