package ru.aleksanderSil4enko.todoproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aleksanderSil4enko.todoproject.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
