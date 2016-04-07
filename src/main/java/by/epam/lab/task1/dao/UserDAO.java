package by.epam.lab.task1.dao;


import by.epam.lab.task1.model.User;

public interface UserDAO {
    public boolean insertUser(User user);
    public User findUserById(long userId);
    public boolean updateUserById(User user, long userId);
    public boolean deleteUserById(long userId);

}
