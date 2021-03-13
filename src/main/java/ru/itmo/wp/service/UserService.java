package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Librarian;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.LibrarianCredentials;
import ru.itmo.wp.repository.LibrarianRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public boolean isLoginVacant(String fullName) {
        return userRepository.countByFullName(fullName) == 0;
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

}
