package by.epam.lab.task.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This entity is used for searching news accordingly the certain search criteria.
 * @author Ivan Dzinhala
 */
public class SearchCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Author author;
    private ArrayList<Tag> tags;

    public SearchCriteria(){}

    public SearchCriteria(Author author, ArrayList<Tag> tags) {
        this.author = author;
        this.tags = tags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }
    public void addTag(Tag tag){
        if(tags!=null){
            tags.add(tag);
        }else{
            tags = new ArrayList<>();
            tags.add(tag);
        }
    }


}
