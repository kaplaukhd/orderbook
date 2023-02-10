package ru.kata.spring.boot_security.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.auth.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Override
    List<User> findAll();

    Optional<User> findUserByName(String name);
    User findUserById(Long id);

    User findUserByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User entity);

    @Override
    void deleteAll();
}
