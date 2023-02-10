package ru.kata.spring.boot_security.demo.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.auth.Role;
import ru.kata.spring.boot_security.demo.auth.User;
import ru.kata.spring.boot_security.demo.repository.auth.RoleRepo;
import ru.kata.spring.boot_security.demo.repository.auth.UserRepo;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepo.findAll();
    }


    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByName(String name) {
        return userRepo.findUserByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return userRepo.findUserById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public List<User> findAllById(Iterable<Long> longs) {
        return userRepo.findAllById(longs);
    }

    @Override
    @Transactional
    public <S extends User> S save(S entity) {
        Set<Role> defaultUserRole = new HashSet<>();
        Role role = new Role(entity.getId(), "ROLE_USER");
        defaultUserRole.add(role);
        roleRepo.saveAndFlush(role);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setRoles(defaultUserRole);
        return userRepo.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {
        userRepo.deleteById(aLong);
    }

    @Override
    @Transactional
    public void delete(User entity) {
        userRepo.delete(entity);
    }

    @Override
    @Transactional
    public void deleteAll() {
        userRepo.deleteAll();
    }


}
