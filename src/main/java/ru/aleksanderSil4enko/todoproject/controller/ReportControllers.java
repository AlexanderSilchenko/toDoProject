package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.aleksanderSil4enko.todoproject.model.Report;
import ru.aleksanderSil4enko.todoproject.model.User;
import ru.aleksanderSil4enko.todoproject.service.ReportService;
import ru.aleksanderSil4enko.todoproject.service.TaskService;
import ru.aleksanderSil4enko.todoproject.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reports")
public class ReportControllers {

    private final ReportService reportService;
    private final UserService userService;
    private final TaskService taskService;

    //обработка иск.ситуации (тип1)
    //с указанием в Header: своего сообщения-"No user present!")
    //с указанием в ответе: класса ошибки NoSuchElementException.class
    //чтоб статус ответа тоже остался ошибкой -"404 NOT FOUND"
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handle(){
        return  new ResponseEntity<>("No such report!", HttpStatus.NOT_FOUND);
    }

    @Autowired
    public ReportControllers(ReportService reportService, UserService userService, TaskService taskService) {
        this.reportService = reportService;
        this.userService = userService;
        this.taskService = taskService;
    }

    //все отчёты
    @GetMapping
    @PreAuthorize("hasAuthority('reports:read')")
    public List<Report> getAll(){
        return reportService.findAll();
    }

    //отчёт по id
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('reports:read')")
    public Report reportInfo(@PathVariable long id) {
        return reportService.findById(id);
    }

    //добавить отчёт
    @PostMapping
    @PreAuthorize("hasAuthority('reports:write')")
    public Report create(@RequestBody Report report) {
        return reportService.save(report);
    }

    //удалить отчёт по id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('all:permissions')")
    public void delete(@PathVariable long id) {
        reportService.delete(id);
    }

    //обновить отчёт полностью
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report update(@PathVariable long id,
                       @RequestBody Report report) {
        return reportService.update(id, report);
    }

    //частично обновить отчёт
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report partialUpdate(@PathVariable long id,
                              @RequestBody Report report) {
        return reportService.partialUpdate(id, report);
    }

    // где добавить функционал создания отчёта пользователем?
    @PostMapping("/add_employer/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report addReportToUser(@PathVariable long id,
                                  @RequestParam(name="user", required = true) long userId) {
        return reportService.addUser(id, userService.findById(userId));
    }

    @PostMapping("/add_task/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report addReportToTask(@PathVariable long id,
                                  @RequestParam(name="task", required = true) long taskId) {
        return reportService.addTask(id, taskService.findById(taskId));
    }

    @PostMapping("/remove_employer/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report removeReportFromUser(@PathVariable long id) {
        return reportService.removeUser(id);
    }

    @PostMapping("/remove_task/{id}")
    @PreAuthorize("hasAuthority('reports:write')")
    public Report removeReportFromTask(@PathVariable long id) {
        return reportService.removeTask(id);
    }

}
