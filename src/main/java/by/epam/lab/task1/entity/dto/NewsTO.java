package by.epam.lab.task1.entity.dto;

import by.epam.lab.task1.entity.Author;
import by.epam.lab.task1.entity.Comment;
import by.epam.lab.task1.entity.News;
import by.epam.lab.task1.entity.Tag;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This entity is used for transfering data between News, Author, Tags and Comments.
 * @see Comment,Tag,Author,News
 * @author Ivan Dzinhala
 */
public class NewsTO implements Serializable{

    private static final long serialVersionUID = 1L;

    private News news;
    private Author author;
    private ArrayList<Tag> tagList;
    private ArrayList<Comment> commentList;


    public NewsTO() {}

    public NewsTO(News news, Author author, ArrayList<Tag> tagList, ArrayList<Comment> commentList) {
        this.news = news;
        this.author = author;
        this.tagList = tagList;
        this.commentList = commentList;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Tag> tagList) {
        this.tagList = tagList;
    }

    public ArrayList<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTO newsTO = (NewsTO) o;

        if (author != null ? !author.equals(newsTO.author) : newsTO.author != null) return false;
        if (commentList != null ? !commentList.equals(newsTO.commentList) : newsTO.commentList != null) return false;
        if (news != null ? !news.equals(newsTO.news) : newsTO.news != null) return false;
        if (tagList != null ? !tagList.equals(newsTO.tagList) : newsTO.tagList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (tagList != null ? tagList.hashCode() : 0);
        result = 31 * result + (commentList != null ? commentList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsTO{" +
                "news=" + news +
                ", author=" + author +
                ", tagList=" + tagList +
                ", commentList=" + commentList +
                '}';
    }
}
