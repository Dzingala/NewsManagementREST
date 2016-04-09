package by.epam.lab.task1.dao;


import by.epam.lab.task1.model.User;

public interface UserDAO {
    public boolean createUser(User user);
    public User readUserById(long userId);
    public boolean updateUserById(User user);
    public boolean deleteUserById(long userId);

}
