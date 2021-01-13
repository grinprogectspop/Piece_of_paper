package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.DocumentType;

public interface DocumentTypeRepo extends JpaRepository<DocumentType, Long> {
}
