package by.epam.lab.task1.entity.dto;


import by.epam.lab.task1.entity.Role;
import by.epam.lab.task1.entity.User;

import java.io.Serializable;
/**
 * This entity is used for transfering data between Users and their Roles.
 * @see User,Role
 * @author Ivan Dzinhala
 */
public class UserTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private User user;
    private Role role;

    public UserTO(){}

    public UserTO(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTO userTO = (UserTO) o;

        if (role != null ? !role.equals(userTO.role) : userTO.role != null) return false;
        if (user != null ? !user.equals(userTO.user) : userTO.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTO{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
