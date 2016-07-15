package by.epam.lab.task.entity;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

/**
 * This entity is used for representing information about the Tag.
 * @author Ivan Dzinhala
 */
@Entity
@Proxy(lazy=false)
@Table(name = "TAG")
public class Tag {
    private long id;
    private String name;
    public Tag(){}

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "TAG_SEQ")
    @SequenceGenerator(name = "TAG_SEQ", sequenceName = "TAG_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "TAG_ID", unique = true,nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "TAG_NAME", nullable = false,length = 30)
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

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
