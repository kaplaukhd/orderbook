package ru.kata.spring.boot_security.demo.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.auth.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Override
    <S extends Role> S save(S entity);

}
