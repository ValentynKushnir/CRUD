package me.service;

import me.dao.UserDao;
import me.dto.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(UserEntity user)
    {
        userDao.saveUser(user);
    }
    @Override
    @Transactional
    public void removeUser(int id) { userDao.removeUser(id); }
    @Override
    @Transactional
    public List<UserEntity> getListOfAllUsers()
    {
        return userDao.getListOfAllUsers();
    }
    @Override
    @Transactional
    public UserEntity getUser(int id) throws IllegalArgumentException
    {
        return userDao.getUser(id);
    }
    @Override
    @Transactional
    public void clearDataBase()
    {
        userDao.clearDataBase();
    }
    @Override
    @Transactional
    public void updateUser(UserEntity user)
    {
        userDao.updateUser(user);
    }
    @Override
    @Transactional
    public void fillWithFakeData()
    {
        userDao.fillWithFakeData();
    }
}
