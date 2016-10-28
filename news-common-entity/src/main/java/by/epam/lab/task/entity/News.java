package by.epam.lab.task.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * This entity is used for representing information about News.
 * @author Ivan Dzinhala
 */
@Entity
@Proxy(lazy=false)
//@org.hibernate.annotations.Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
@Table(name = "NEWS")
public class News {
    private long id;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Date modificationDate;
    public News(){}

    public News(long id, String title, String shortText, String fullText, Timestamp creationDate, Date modificationDate) {
        this.id = id;
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "NEWS_SEQ")
//    @SequenceGenerator(name = "NEWS_SEQ", sequenceName = "NEWS_SEQ", allocationSize = 1, initialValue = 1)
    @Column(name = "NEWS_ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "TITLE", nullable = false, length = 30)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name = "SHORT_TEXT", nullable = false, length = 100)
    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }
    @Column(name = "FULL_TEXT", nullable = false, length = 2000)
    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    @Type(type = "timestamp")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "MODIFICATION_DATE", nullable = false)
    @Type(type = "date")
    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (!title.equals(news.title)) return false;
        if (!shortText.equals(news.shortText)) return false;
        if (!fullText.equals(news.fullText)) return false;
        if (!creationDate.equals(news.creationDate)) return false;
        return modificationDate.equals(news.modificationDate);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + shortText.hashCode();
        result = 31 * result + fullText.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + modificationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortText='" + shortText + '\'' +
                ", fullText='" + fullText + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
