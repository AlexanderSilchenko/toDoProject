package ru.aleksanderSil4enko.todoproject.model;

public enum Permission {
    // EMPLOYER - Ставить задачи себе, устанавливать и корректировать сроки для своих задач (кроме тех,
    // что установлены нач.), комментировать свои задачи, создаёт отчёты и комментирует выполнение задач
    // MASTER - Ставить задачи подчиненым по отделу и себе, устанавливать и корректировать сроки,
    // комментировать задачи своего отдела, читает отчёты подчиненых, отмечает выполненые задачи
    // CHIEF - Ставить задачи, устанавливать и корректировать сроки, комментировать задачи
    // по всем отделам, утверждает выполнение.
    // ADMIN - Добавлять сотрудников, подразделения, менять подчиненость (порядок)
    EMPLOYERS_TASK_READ("tasks:read"),
    TASK_WRITE("tasks:write"),
    COMMENT_READ("comments:read"),
    COMMENT_WRITE("comments:write"),
    REPORT_READ("reports:read"),
    REPORT_WRITE("reports:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
