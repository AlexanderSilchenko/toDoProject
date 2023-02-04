package ru.aleksanderSil4enko.todoproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aleksanderSil4enko.todoproject.service.PersonService;
import ru.aleksanderSil4enko.todoproject.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportControllers {

    private final ReportService reportService;
    private final PersonService personService;

    @Autowired
    public ReportControllers(ReportService reportService, PersonService personService) {
        this.reportService = reportService;
        this.personService = personService;
    }

    //все отчёты
    //отчёты по id
    //добавить отчёт
    //удалить отчёт по id
    //обновить отчёт полностью
    //частично обновить отчёт
    // где добавить функционал создания отчёта пользователем?
}
