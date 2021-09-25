package hillel.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import hillel.model.User;
import hillel.util.UserUtil;

@Repository
public class UserRepository {

    private final Map<Integer, User> userDatabase = new HashMap<>();

    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase.values());
    }

    public void saveUser(User user) {
        setId(user);
        userDatabase.put(user.getId(), user);
    }

    public User getUserById(int id) throws IllegalArgumentException {
        if (!userDatabase.containsKey(id)) {
            throw new IllegalArgumentException("No such user with id: " + id);
        }
        return userDatabase.get(id);
    }

    public void clearDataBase() {
        this.userDatabase.clear();
    }

    public void updateUser(User user) {
        userDatabase.remove(user.getId());
        userDatabase.put(user.getId(), user);
    }

    public void removeUser(int id) {
        userDatabase.remove(id);
    }

    public void fillWithFakeData() {
        clearDataBase();
        for (String userName : UserUtil.bankOfNames) {
            User user = UserUtil.newUser(userName);
            setId(user);
            saveUser(user);
        }
    }

    private void setId(User user) {
        int id;
        do {
            id = (int) (Math.random() * Integer.MAX_VALUE);
        } while (userDatabase.containsKey(id));
        user.setId(id);
    }

}
