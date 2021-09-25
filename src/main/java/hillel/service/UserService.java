package hillel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hillel.model.User;
import hillel.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepositoryImpl) {
        this.userRepository = userRepositoryImpl;
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void removeUser(int id) {
        userRepository.removeUser(id);
    }

    public void fillWithFakeData() {
        userRepository.fillWithFakeData();
    }

    public void clearDataBase() {
        userRepository.clearDataBase();
    }

}
