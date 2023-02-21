package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.Status;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.repository.TaskRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task save(Task task) {
        task.setTaskStatus(Status.CREATED);
        return taskRepository.save(task);
    }

    public void delete(long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task update(long id, Task task) {
        return taskRepository.findById(id)
                .map(entry -> {
                    entry.setTitle(task.getTitle());
                    entry.setDescription(task.getDescription());
                    entry.setEmployers(task.getEmployers());
                    entry.setDateStart(task.getDateStart());
                    entry.setDateFinish(task.getDateFinish());
                    entry.setDateDone(task.getDateDone());
                    entry.setTaskStatus(task.getTaskStatus());
                    entry.setReports(task.getReports());
            return entry;
        }).orElseThrow();
    }
    @Transactional
    public Task partialUpdate(long id, Task task) {
        return taskRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    if (task.getTitle() != null && !task.getTitle().equals(""))
                        entry.setTitle(task.getTitle());
                    if (task.getDescription() != null && !task.getDescription().equals(""))
                        entry.setDescription(task.getDescription());
                    if (task.getEmployers() != null)
                        entry.setEmployers(task.getEmployers());
                    if (task.getDateStart() != null && !task.getDateStart().equals(""))
                        entry.setDateStart(task.getDateStart());
                    if (task.getDateFinish() != null && !task.getDateFinish().equals(""))
                        entry.setDateFinish(task.getDateFinish());
                    if (task.getDateDone() != null && !task.getDateDone().equals(""))
                        entry.setDateDone(task.getDateDone());
                    if (task.getTaskStatus() != null && !task.getTaskStatus().equals(""))
                        entry.setTaskStatus(task.getTaskStatus());
                    if (task.getReports() != null)
                        entry.setReports(task.getReports());
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Task addUser(long id, User user) {
        return taskRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    List tasksEmployers = entry.getEmployers();
                    tasksEmployers.add(user);
                    entry.setEmployers(tasksEmployers);
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Task removeUser(long id, User user) {
        return taskRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    List tasksEmployers = entry.getEmployers();
                    tasksEmployers.remove(user);
                    entry.setEmployers(tasksEmployers);
                    return entry;
                }).orElseThrow();
    }
}
