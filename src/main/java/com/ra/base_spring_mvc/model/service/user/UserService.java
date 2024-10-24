package com.ra.base_spring_mvc.model.service.user;

import com.ra.base_spring_mvc.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getListUser();
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(User user);
    User findById(int id);
    boolean checkUsernameExist(String username);
    boolean checkPhoneExist(String phone);
}
