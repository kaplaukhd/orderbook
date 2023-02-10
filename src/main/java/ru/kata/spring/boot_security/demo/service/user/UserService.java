package ru.kata.spring.boot_security.demo.service.user;

import ru.kata.spring.boot_security.demo.auth.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findUserByUsername(String username);

    Optional<User> findUserByName(String name);

    User findUserById(Long id);

    void updateUser(User user);

    List<User> findAllById(Iterable<Long> longs);

    <S extends User> S save(S entity);

    void deleteById(Long aLong);

    void delete(User entity);

    void deleteAll();
}
