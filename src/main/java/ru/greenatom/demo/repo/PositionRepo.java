package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.Position;

public interface PositionRepo extends JpaRepository<Position, Long> {
}
