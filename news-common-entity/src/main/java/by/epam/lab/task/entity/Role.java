package by.epam.lab.task.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * This entity is used for representing information about the Role.
 * @author Ivan Dzinhala
 */
@Entity
@Proxy(lazy=false)
//@Cache(usage= CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name="ROLES")
public class Role {
    private Long id;
    private String name;
    public Role(){}

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "ROLES_SEQ")
    @SequenceGenerator(name = "ROLES_SEQ", sequenceName = "ROLES_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "ROLE_ID", unique = true,nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "ROLE_NAME",nullable = false,length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
