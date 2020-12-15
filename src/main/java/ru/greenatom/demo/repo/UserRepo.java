package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
}
