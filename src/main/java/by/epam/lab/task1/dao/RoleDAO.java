package by.epam.lab.task1.dao;


import by.epam.lab.task1.model.Role;

public interface RoleDAO {
    public boolean createRole(Role role);
    public Role readRoleById(long roleId);
    public boolean updateRoleById(Role role);
    public boolean deleteRoleById(long roleId);
}
