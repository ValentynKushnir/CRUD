package me.dao;

import me.dto.UserEntity;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public interface UserDao {

    void setSessionFactory(SessionFactory sessionFactory);

    void saveUser(UserEntity user);

    List<UserEntity> getListOfAllUsers();

    UserEntity getUser(int id) throws IllegalArgumentException;

    void clearDataBase();

    void updateUser(UserEntity user);

    void removeUser(int id);

    void fillWithFakeData();
}
