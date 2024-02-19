package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    List<User> getUsersList();
    User getUser(Long id);

}
