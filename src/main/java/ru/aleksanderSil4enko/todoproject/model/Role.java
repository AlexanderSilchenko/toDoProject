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
    EMPLOYER(Set.of()),
    MASTER(Set.of()),
    CHIEF(Set.of()),
    ADMIN(Set.of());

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
