package me.service;

import me.dao.UserDao;
import me.dto.UserEntity;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void setUserDao(UserDao userDao);

    void saveUser(UserEntity user);

    void removeUser(int id);

    List<UserEntity> getListOfAllUsers();

    UserEntity getUser(int id) throws IllegalArgumentException;

    void clearDataBase();

    void updateUser(UserEntity user);

    void fillWithFakeData();
}
