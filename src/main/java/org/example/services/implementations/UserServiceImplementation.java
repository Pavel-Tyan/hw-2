package org.example.services.implementations;

import lombok.AllArgsConstructor;
import org.example.exceptions.ResourceAlreadyExistsException;
import org.example.exceptions.ResourceNotFoundException;
import org.example.models.User;
import org.example.repositories.UserRepository;
import org.example.services.UserService;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    @Override
    public boolean isUserExists(User user) {
        if (user == null)
            return false;

        for (User currentUser: userRepository.getUsers()) {
            if (user.equals(currentUser)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.getUsers();
    }

    @Override
    public void addNewUser(User user) {
        for (User currentUser: userRepository.getUsers()) {
            if (Objects.equals(user.getLogin(), currentUser.getLogin())) {
                throw new ResourceAlreadyExistsException("Пользователь с таким логином уже существует.");
            }
        }

        userRepository.getUsers().add(user);
        userRepository.serializeUsers();
    }

    @Override
    public void deleteUser(String login) {
        for (User currentUser: userRepository.getUsers()) {
            if (login.equals(currentUser.getLogin())) {
                userRepository.getUsers().remove(currentUser);
                userRepository.serializeUsers();
                return;
            }
        }

        throw new ResourceNotFoundException("Такого пользователя не существует");
    }
}
