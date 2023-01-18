package ru.aleksanderSil4enko.todoproject.model;

public enum Permission {
    TASKS_READ("tasks:read"),
    TASKS_WRITE("tasks:write"),
    COMMENTS_READ("comments:read"),
    COMMENTS_WRITE("comments:write"),
    REPORTS_READ("reports:read"),
    REPORTS_WRITE("reports:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
