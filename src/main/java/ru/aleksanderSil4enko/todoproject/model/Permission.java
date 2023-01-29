package ru.aleksanderSil4enko.todoproject.model;

public enum Permission {

    TASK_READ("tasks:read"),
    TASK_WRITE("tasks:write"),
    COMMENT_READ("comments:read"),
    COMMENT_WRITE("comments:write"),
    REPORT_READ("reports:read"),
    REPORT_WRITE("reports:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
