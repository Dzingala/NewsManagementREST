package by.epam.lab.task1.model;


import java.sql.Timestamp;

public class Author {
    private long authorId;
    private String authorName;
    private Timestamp expired;
    public Author(){}
    public Author(long authorId, String authorName, Timestamp expired) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.expired = expired;
    }


    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != author.authorId) return false;
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) return false;
        if (expired != null ? !expired.equals(author.expired) : author.expired != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (expired != null ? expired.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", expired=" + expired +
                '}';
    }
}
