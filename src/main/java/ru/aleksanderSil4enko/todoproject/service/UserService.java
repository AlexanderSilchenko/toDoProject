package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    public User update(long id, User user) {
        return userRepository.findById(id)
                .map(entry -> {
                    entry.setEmail(user.getEmail());
                    entry.setPassword(user.getPassword());
                    entry.setRole(user.getRole());
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public User partialUpdate(long id, User user) {
        return userRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    if (user.getEmail() != null && !user.getEmail().equals(""))
                        entry.setEmail(user.getEmail());
                    if (user.getPassword() != null && !user.getPassword().equals(""))
                        entry.setPassword(user.getPassword());
                    if (user.getRole() != null)
                        entry.setRole(user.getRole());
                    return entry;
                }).orElseThrow();
    }
}
