package by.epam.lab.task.entity.dto;

import by.epam.lab.task.entity.News;

import java.io.Serializable;
import java.util.ArrayList;

public class NewsTORecord implements Serializable {
    private News news;
    private Long authorId;
    private ArrayList<Long> tagIdList;
    public NewsTORecord(){}

    public NewsTORecord(News news, Long authorId, ArrayList<Long> tagIdList) {
        this.news = news;
        this.authorId = authorId;
        this.tagIdList = tagIdList;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public ArrayList<Long> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(ArrayList<Long> tagIdList) {
        this.tagIdList = tagIdList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsTORecord that = (NewsTORecord) o;

        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        if (news != null ? !news.equals(that.news) : that.news != null) return false;
        if (tagIdList != null ? !tagIdList.equals(that.tagIdList) : that.tagIdList != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = news != null ? news.hashCode() : 0;
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (tagIdList != null ? tagIdList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NewsTORecord{" +
                "news=" + news +
                ", authorId=" + authorId +
                ", tagIdList=" + tagIdList +
                '}';
    }
}
