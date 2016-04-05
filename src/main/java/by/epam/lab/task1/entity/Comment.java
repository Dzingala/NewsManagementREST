package by.epam.lab.task1.entity;


import java.sql.Timestamp;

public class Comment {
    private long commentId;
    private long newsId;
    private String commentText;
    private Timestamp creationDate;

    public Comment(long commentId, long newsId, String commentText, Timestamp creationDate) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.commentText = commentText;
        this.creationDate = creationDate;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

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

        if (commentId != comment.commentId) return false;
        if (newsId != comment.newsId) return false;
        if (commentText != null ? !commentText.equals(comment.commentText) : comment.commentText != null) return false;
        if (creationDate != null ? !creationDate.equals(comment.creationDate) : comment.creationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

}
