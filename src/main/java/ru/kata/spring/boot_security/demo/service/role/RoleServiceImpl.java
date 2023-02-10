package ru.kata.spring.boot_security.demo.service.role;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.auth.Role;
import ru.kata.spring.boot_security.demo.repository.auth.RoleRepo;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }


    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }

    @Override
    public void addRole(Role role) {
        roleRepo.save(role);
    }
}
