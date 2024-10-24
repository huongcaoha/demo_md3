package com.ra.base_spring_mvc.model.dao.user;

import com.ra.base_spring_mvc.model.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getListUser();
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    User findById(int id);
}
