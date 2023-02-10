package ru.kata.spring.boot_security.demo.service.role;

import ru.kata.spring.boot_security.demo.auth.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRoles();

    public void addRole(Role role);
}
