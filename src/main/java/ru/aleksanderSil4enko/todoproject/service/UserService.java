package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.*;
import ru.aleksanderSil4enko.todoproject.repository.CommentRepository;
import ru.aleksanderSil4enko.todoproject.repository.ReportRepository;
import ru.aleksanderSil4enko.todoproject.repository.TaskRepository;
import ru.aleksanderSil4enko.todoproject.repository.UserRepository;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final CommentRepository commentRepository;
    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public String hashPassword(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        return encodedPassword;
    }

    public boolean controlEmail(User user) {
        boolean matchesEmail = user.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (matchesEmail == false) {
            System.out.println(String.format("ERROR: ", user.getEmail()));
        }
        return matchesEmail;
    }

    public User create(User user) {
        boolean result = controlEmail(user);
        if (!result) {
            throw new EntityExistsException("Bad email");
        }
        AtomicInteger flagEmail = new AtomicInteger(0);
        List<User> users = userRepository.findAll();
        users.forEach(entry -> {
                    if (entry.getEmail().equals(user.getEmail())) {
                        flagEmail.set(1);
                    }
                }
        );
        if (flagEmail.get() == 1) {
            throw new IllegalArgumentException("Exists email");
        }
        user.setRole(Role.EMPLOYER);
        user.setPassword(hashPassword(user));
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setTitle(user.getTitle());
        user.setDepartment(user.getDepartment());
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.findById(id).orElseThrow();
        userRepository.deleteById(id);
    }
    @Transactional
    public User setRoleUser(long id, Role role){
        return userRepository.findById(id)
                .map(entry -> {
                    entry.setRole(role);
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public User update(long id, User user) {
        return userRepository.findById(id)
                .map(entry -> {
                    entry.setEmail(user.getEmail());
                    entry.setPassword(hashPassword(user));
                    entry.setFirstName(user.getFirstName());
                    entry.setLastName(user.getLastName());
                    entry.setTitle(user.getTitle());
                    entry.setDepartment(user.getDepartment());
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
                        entry.setPassword(hashPassword(user));
                    if (user.getFirstName() != null && !user.getFirstName().equals(""))
                        entry.setFirstName(user.getFirstName());
                    if (user.getLastName() != null && !user.getLastName().equals(""))
                        entry.setLastName(user.getLastName());
                    if (user.getTitle() != null && !user.getTitle().equals(""))
                        entry.setTitle(user.getTitle());
                    if (user.getDepartment() != null && !user.getDepartment().equals(""))
                        entry.setDepartment(user.getDepartment());
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public User addTask(long userId, Task task) {
        taskRepository.save(task);
        return userRepository.findById(userId)
                .map(entry -> {
                    task.setEmployers(Collections.singletonList(entry));
                    task.setTaskStatus(Status.CREATED);
                    log.info(String.valueOf(task));
                    log.info(String.valueOf(entry));
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public User addTasksReport(long userId, long taskId, Report report) {
        //добавить проверку есть ли задание у работника
        Task task = taskRepository.findById(taskId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();
        if (task.getEmployers().contains(user)) {
            report.setTask(task);
            report.setEmployer(user);
            log.info(String.valueOf(report));
            reportRepository.save(report);
        } else return user;
        return report.getEmployer();
    }
    @Transactional
    public User addReportsComment(long userId, long reportId, Comment comment) {
        User user = userRepository.findById(userId).orElseThrow();
        Report report = reportRepository.findById(reportId).orElseThrow();
        if (report.getEmployer().equals(user)) {
            comment.setEmployer(user);
            comment.setReport(report);
            comment.setTimestamp(new Timestamp(System.currentTimeMillis()));
            log.info(String.valueOf(comment));
            commentRepository.save(comment);
        }
        return user;
    }
}
