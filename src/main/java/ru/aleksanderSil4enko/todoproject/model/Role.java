package ru.aleksanderSil4enko.todoproject.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    // EMPLOYER - Ставить задачи себе, устанавливать и корректировать сроки для своих задач (кроме тех,
    // что установлены нач.), комментировать свои задачи, создаёт отчёты и комментирует выполнение задач
    // MASTER - Ставить задачи подчиненым по отделу и себе, устанавливать и корректировать сроки,
    // комментировать задачи своего отдела, читает отчёты подчиненых, отмечает выполненые задачи
    // CHIEF - Ставить задачи, устанавливать и корректировать сроки, комментировать задачи
    // по всем отделам, утверждает выполнение.
    // ADMIN - Добавлять сотрудников, подразделения, менять подчиненость (порядок)
    EMPLOYER(Set.of(Permission.REPORT_WRITE,
            Permission.REPORT_READ,
            Permission.TASK_READ,
            Permission.COMMENT_READ)),
    MASTER(Set.of(Permission.TASK_WRITE,
            Permission.TASK_READ,
            Permission.REPORT_WRITE,
            Permission.REPORT_READ,
            Permission.COMMENT_READ,
            Permission.USERS_READ)),
    CHIEF(Set.of(Permission.TASK_WRITE,
            Permission.TASK_READ,
            Permission.REPORT_WRITE,
            Permission.REPORT_READ,
            Permission.COMMENT_WRITE,
            Permission.COMMENT_READ,
            Permission.USERS_READ)),
    ADMIN(Set.of(Permission.TASK_WRITE,
            Permission.TASK_READ,
            Permission.REPORT_WRITE,
            Permission.REPORT_READ,
            Permission.COMMENT_WRITE,
            Permission.COMMENT_READ,
            Permission.USERS_WRITE,
            Permission.USERS_READ,
            Permission.ROLE_WRITE,
            Permission.ALL_PERMISSIONS));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getAuthorities() {
        return this.permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
