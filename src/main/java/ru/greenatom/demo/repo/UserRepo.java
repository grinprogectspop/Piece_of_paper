package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.User;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByEmail(String email);
    User findByUserId(Long userId);
}
