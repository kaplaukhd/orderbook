package ru.kata.spring.boot_security.demo.service.userDetails;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.OrderEntity;
import ru.kata.spring.boot_security.demo.auth.User;
import ru.kata.spring.boot_security.demo.repository.auth.UserRepo;


@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;

    public UserDetailServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with name: %s not found", username));
        }
        try {
            Hibernate.initialize(user.getRoles());
            Hibernate.initialize(new OrderEntity().getOrderContent());
        } catch (HibernateException e) {
           log.error(e.getMessage());
        }
        return user;
    }
}
