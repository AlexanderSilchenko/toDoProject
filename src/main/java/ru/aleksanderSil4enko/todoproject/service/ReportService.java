package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.model.Report;
import ru.aleksanderSil4enko.todoproject.model.Task;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.repository.ReportRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Report findById(long id) {
        return reportRepository.findById(id).orElseThrow();
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void delete(long id) {
        removeUser(id);
        removeTask(id);
        reportRepository.deleteById(id);
    }
    @Transactional
    public Report update(long id, Report report) {
        return reportRepository.findById(id)
                .map(entry -> {
                    entry.setDate(report.getDate());
                    entry.setTimeStart(report.getTimeStart());
                    entry.setTimeStop(report.getTimeStop());
                    return entry;
                }).orElseThrow();
    }

    @Transactional
    public Report partialUpdate(long id, Report report) {
        return reportRepository.findById(id)
                .map(entry -> {
                    if (report.getDate() != null)
                        entry.setDate(report.getDate());
                    if (report.getTimeStart() != null)
                        entry.setTimeStart(report.getTimeStart());
                    if (report.getTimeStop() != null)
                        entry.setTimeStop(report.getTimeStop());
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Report addUser(long id, User user) {
        return reportRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    entry.setEmployer(user);
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Report addTask(long id, Task task) {
        return reportRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    entry.setTask(task);
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Report removeUser(long id) {
        return reportRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    entry.setEmployer(null);
                    return entry;
                }).orElseThrow();
    }
    @Transactional
    public Report removeTask(long id) {
        return reportRepository.findById(id)
                .map(entry -> {
                    log.info(entry.toString());
                    entry.setTask(null);
                    return entry;
                }).orElseThrow();
    }
}
