package by.epam.lab.task.entity;


import javax.persistence.*;
import java.sql.Timestamp;
/**
 * This entity is used for representing information about a Comment.
 * @author Ivan Dzinhala
 */
@Entity
@Table(name = "COMMENTS")
public class Comment {
    private long id;
    private long newsId;
    private String text;
    private Timestamp creationDate;
    public Comment(){}
    public Comment(long id, long newsId, String text, Timestamp creationDate) {
        this.id = id;
        this.newsId = newsId;
        this.text = text;
        this.creationDate = creationDate;
    }
    @Id
    @Column(name = "COMMENT_ID", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column(name = "NEWS_ID", nullable = false)
    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId)
    {
        this.newsId = newsId;
    }

    @Column(name = "COMMENT_TEXT", nullable = false,length =100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "CREATION_DATE", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (newsId != comment.newsId) return false;
        if (creationDate != null ? !creationDate.equals(comment.creationDate) : comment.creationDate != null)
            return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", newsId=" + newsId +
                ", text='" + text + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
