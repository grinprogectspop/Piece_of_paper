package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.DocumentHistory;

import java.util.List;

public interface DocumentHistoryRepo extends JpaRepository<DocumentHistory, Long> {
    DocumentHistory findByDocumentHistoryId(Long iterable);
    List<DocumentHistory> findByAction(Action action);
}
