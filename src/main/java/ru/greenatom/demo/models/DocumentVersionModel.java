package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentHistory;

import java.time.LocalDateTime;
import java.util.Set;


@Data
public class DocumentVersionModel {

    private Long documentVersionId;
    private String versionName;
    private LocalDateTime date;
    private String url;
    private Set<DocumentHistory> documentChanges;
    private Document document;
}
