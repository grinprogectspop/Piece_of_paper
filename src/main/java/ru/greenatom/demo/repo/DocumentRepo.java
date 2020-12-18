package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.Document;

public interface DocumentRepo extends JpaRepository<Document, Long> {

    Document findByDocumentId(Long id);
}
