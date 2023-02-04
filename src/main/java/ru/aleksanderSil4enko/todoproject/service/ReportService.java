package ru.aleksanderSil4enko.todoproject.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aleksanderSil4enko.todoproject.repository.ReportRepository;

@Service
@AllArgsConstructor
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
}
