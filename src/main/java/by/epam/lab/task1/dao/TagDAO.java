package by.epam.lab.task1.dao;


import by.epam.lab.task1.model.Tag;

public interface TagDAO {
    public boolean createTag(Tag role);
    public Tag readTagById(long roleId);
    public boolean updateTagById(Tag tag);
    public boolean deleteTagById(long roleId);
}
