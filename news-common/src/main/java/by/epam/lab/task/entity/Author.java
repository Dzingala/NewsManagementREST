package by.epam.lab.task.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
/**
 * This entity is used for representing information about an Author.
 * @author Ivan Dzinhala
 */
@Entity
@Table(name = "AUTHOR")
public class Author {
    private long id;
    private String name;
    private Timestamp expired;
    public Author(){}

    public Author(long id, String name, Timestamp expired) {
        this.id = id;
        this.name = name;
        this.expired = expired;
    }
    @Id
    @Column(name = "AUTHOR_ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "AUTHOR_NAME", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "EXPIRED", nullable = true)
    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired() {
        this.expired = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (expired != null ? !expired.equals(author.expired) : author.expired != null) return false;
        if (name != null ? !name.equals(author.name) : author.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +//=StringBuilder
                "id=" + id +
                ", name='" + name + '\'' +
                ", expired=" + expired +
                '}';
    }
}
