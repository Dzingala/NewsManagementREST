package by.epam.lab.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This entity is used for searching news accordingly the certain search criteria.
 * @author Ivan Dzinhala
 */
public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long authorId;
    private ArrayList<Long> tagsId;

    public SearchCriteria(){}

    public SearchCriteria(Long authorId, ArrayList<Long> tagsId) {
        this.authorId = authorId;
        this.tagsId = tagsId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public ArrayList<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(ArrayList<Long> tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "authorId=" + authorId +
                ", tagsId=" + tagsId +
                '}';
    }
}
