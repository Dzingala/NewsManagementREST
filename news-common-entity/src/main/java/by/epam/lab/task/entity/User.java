package by.epam.lab.task.entity;

import by.epam.lab.task.md5util.MD5Hashing;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * This entity is used for representing information about User.
 * @author Ivan Dzinhala
 */
@Entity
//@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
@Proxy(lazy=false)
@Table(name = "USERS")
public class User {
    private Long id;
    private String name;
    private String login;
    private String password;
    private Long roleId;

    public User(){}

    public User(Long roleId, Long id, String name, String login, String password) {
        this.roleId = roleId;
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }
    public void setReadyPassword(String password){this.password=password;}

    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "USERS_SEQ")
    @SequenceGenerator(name = "USERS_SEQ", sequenceName = "USERS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "USER_ID",unique = true,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="USER_NAME",nullable = false,length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="LOGIN",nullable = false,length = 30)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @Column(name="PASSWORD",nullable = false,length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5Hashing.md5(password);
    }

    @Column(name = "ROLE_ID",nullable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (roleId != null ? !roleId.equals(user.roleId) : user.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
