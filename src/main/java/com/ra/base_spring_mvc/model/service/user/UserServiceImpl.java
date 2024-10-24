package com.ra.base_spring_mvc.model.service.user;

import com.ra.base_spring_mvc.model.dao.user.UserDAO;
import com.ra.base_spring_mvc.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    public List<User> getListUser() {
        return userDAO.getListUser();
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return userDAO.deleteUser(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public boolean checkUsernameExist(String _username) {
        List<User> users = getListUser();
       return users.stream().map(User::getUsername).anyMatch(username -> username.equals(_username));
    }

    @Override
    public boolean checkPhoneExist(String _phone) {
        List<User> users = getListUser();
        return users.stream().map(User::getPhone).anyMatch(phone -> phone.equals(_phone));
    }


}
