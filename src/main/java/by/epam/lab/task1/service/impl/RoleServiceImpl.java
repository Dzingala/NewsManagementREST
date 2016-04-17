package by.epam.lab.task1.service.impl;

import by.epam.lab.task1.entity.Role;
import by.epam.lab.task1.exceptions.dao.DAOException;
import by.epam.lab.task1.exceptions.dao.NoSuchEntityException;
import by.epam.lab.task1.exceptions.service.ServiceException;
import by.epam.lab.task1.repository.RoleRepository;
import by.epam.lab.task1.service.RolesService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author Ivan Dzinhala
 * @see RolesService
 */
@Service("roleService")
public class RoleServiceImpl implements RolesService{

    private final static Logger logger= Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long create(Role role) throws ServiceException {
        logger.debug("Creating role in RoleService");
        Long roleId=null;
        try{
            roleId=roleRepository.create(role);
        } catch (DAOException e) {
            logger.error("DAOException while creating role in RoleService");
            throw new ServiceException("ServiceException while creating role",e);
        }
        return roleId;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role readById(Long roleId) throws ServiceException, NoSuchEntityException {
        logger.debug("Reading role in RoleService");
        Role role =null;
        try{
            role=roleRepository.read(roleId);
        }catch (NoSuchEntityException e){
            logger.debug("NoSuchEntityException while reading Role in RoleService");
            throw new NoSuchEntityException(e);
        } catch (DAOException e) {
            logger.error("DAOException while reading role by id in RoleService");
            throw new ServiceException("ServiceException while reading role by id",e);
        }
        return role;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Role role) throws ServiceException {
        logger.debug("Updating role in RoleService");
        try {
            roleRepository.update(role.getId(), role);
        }catch (DAOException e) {
            logger.error("DAOException while updating role in RoleService");
            throw new ServiceException("ServiceException while updating role",e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Role role) throws ServiceException {
        logger.debug("Deleting role in RoleService");
        try{
            roleRepository.delete(role.getId());
        } catch (DAOException e) {
            logger.error("DAOException while deleting role in RoleService");
            throw new ServiceException("ServiceException while deleting role",e);
        }

    }
}
