package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.SecrecyLevel;

public interface SecrecyLevelRepo extends JpaRepository<SecrecyLevel, Long> {
}
