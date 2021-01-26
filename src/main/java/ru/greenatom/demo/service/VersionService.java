package ru.greenatom.demo.service;

import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentHistory;
import ru.greenatom.demo.domain.DocumentVersion;
import ru.greenatom.demo.repo.DocumentVersionRepo;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VersionService {

    private final DocumentVersionRepo documentVersionRepo;

    public VersionService(DocumentVersionRepo documentVersionRepo) {
        this.documentVersionRepo = documentVersionRepo;
    }

    public DocumentVersion saveVersion(DocumentVersion previousVersion, Document document, DocumentHistory documentHistory) {
        DocumentVersion version = new DocumentVersion();

        version.setVersionName(updateVersionName(previousVersion.getVersionName()));
        version.setDate(LocalDateTime.now());
        version.setUrl(previousVersion.getUrl());
        version.setDocument(document);

        version.setDocumentChanges(document.getChanges());
        version.getDocumentChanges().add(documentHistory);

        documentVersionRepo.save(version);

        return version;
    }

    public String updateVersionName(String oldVersion) {
        StringBuilder sb = new StringBuilder();

        // String -> List<Integer>
        // ("0.0.9.9" -> { 0, 0, 9, 9 })
        List<Integer> versionNums = Arrays.stream(
                Arrays.stream(oldVersion.split("\\."))
                        .mapToInt(String::length)
                        .toArray())
                .boxed()
                .collect(Collectors.toList());

        // Incrementing old version:
        // "0.0.9.9" -> "0.0.9.10"
        versionNums.set(versionNums.size() - 1, versionNums.get(versionNums.size() - 1) + 1);

        // Example:
        // Old version (before "for"): "0.0.9.10"
        // New version (after "for"): "0.1.0.0"
        for (int i = 0; i < versionNums.size() ; i++) {
            if (i != versionNums.size() - 1 && versionNums.get(versionNums.size() - 1 - i) == 10) {
                versionNums.set(versionNums.size() - i, 0);
                versionNums.set(versionNums.size() - i + 1, versionNums.get(versionNums.size() - i + 1) + 1);
            }
        }

        for (Integer i : versionNums) {
            sb.append(i);
            sb.append(".");
        }

        return sb.toString();
    }
}
