package ru.greenatom.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.DocumentAccess;

import java.util.List;

public interface DocumentAccessRepo extends JpaRepository<DocumentAccess, Long> {
    List<Action> findActionByUserIdAndDocumentId(Long userId, Long documentId);
}
