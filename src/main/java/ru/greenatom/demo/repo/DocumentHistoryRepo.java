package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.DocumentHistory;

public interface DocumentHistoryRepo extends JpaRepository<DocumentHistory, Long> {
    DocumentHistory findByDocumentHistoryId(Long iterable);
}
