package me.dao;

import me.dto.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDaoImplMySQL implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImplMySQL.class);
    private SessionFactory sessionFactory;
    private Session session;
    private static final List<String> bankOfNames = new ArrayList<>();
    private static final Long tenYearsInMillis = 315_360_000_000L;
    static {
        bankOfNames.addAll(
                Arrays.asList(
                        "Fred Clark", "Rosemary Clooney", "Alan Curtis", "Laird Cregar", "Laird Cregar", "Neil Diamond", "Joan Davis", "Hal David",
                        "Gloria DeHaven", "Bruce Dern", "Ken Ehrlich", "Dale Evans",  "Dick Foran", "Eddie Foy", "Eddie Fisher", "Al Goodman",
                        "Dan Avey" ,"Gene Autry","Dorothy Arzner","Eddy Arnold","Desi Arnaz","Olive Borden","Anne Bancroft","Bob Barker","Lucille Ball",
                        "Count Basie","John Barrymore","Bob Crosby","Glenn Close","Lee Daniels","Bobby Driscoll"
                ));
    }

    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveUser(UserEntity user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
        logger.info("User was successfully saves. User details: " + user);
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> getListOfAllUsers() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserEntity> users = session.createQuery("from UserEntity").list();
        session.getTransaction().commit();
        session.close();
        for (UserEntity user : users)
            logger.info("User was successfully downloaded. User details: " + user);
        return users;
    }
    @Override
    public UserEntity getUser(int id) throws IllegalArgumentException {
        session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, id);
        session.getTransaction().commit();
        session.close();
        if(user == null)
            throw new IllegalArgumentException();
        logger.info("User was successfully downloaded. User details: " + user);
        return user;
    }
    @Override
    public void clearDataBase() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete from UserEntity");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        logger.info("The database has been cleared");
    }

    @Override
    public void updateUser(UserEntity user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        logger.info("User was successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        UserEntity user = session.load(UserEntity.class, id);
        if(user != null)
            session.delete(user);
        session.getTransaction().commit();
        session.close();
        logger.info("User was successfully removed. User details: " + user);
    }

    @Override
    public void fillWithFakeData() {
        clearDataBase();
        int times = new Random().nextInt(5) + 5;
        for (int i = 0; i < times; i++) {
            UserEntity user = new UserEntity();
            user.setName(bankOfNames.get(new Random().nextInt(bankOfNames.size())));
            user.setCreatedDate(new Date((long)(System.currentTimeMillis() - new Random().nextDouble() * tenYearsInMillis*1.0)));
            user.setIsAdmin(new Random().nextBoolean());
            user.setAge(new Random().nextInt(20) + 20);
            saveUser(user);
        }
    }
}
