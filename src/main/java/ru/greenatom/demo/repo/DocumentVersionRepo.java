package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.DocumentVersion;

public interface DocumentVersionRepo extends JpaRepository<DocumentVersion, Long> {
}
