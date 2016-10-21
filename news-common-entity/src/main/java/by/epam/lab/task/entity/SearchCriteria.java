package by.epam.lab.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
<<<<<<< HEAD:news-common/src/main/java/by/epam/lab/task/entity/SearchCriteria.java
import java.util.List;
=======
>>>>>>> develop/netcracker:news-common-entity/src/main/java/by/epam/lab/task/entity/SearchCriteria.java

/**
 * This entity is used for searching news accordingly the certain search criteria.
 * @author Ivan Dzinhala
 */
public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long authorId;
    private List<Long> tagsId;

    public SearchCriteria(){}

    public SearchCriteria(Long authorId, List<Long> tagsId) {
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

    public List<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<Long> tagsId) {
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
