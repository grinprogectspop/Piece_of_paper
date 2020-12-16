package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class DocumentModel {

    private Long documentId;
    private String documentName;
    private LocalDateTime creationDate;
    private String password;
    private boolean deleted;
    private Set<Action> accessTypes;
    private DocumentType documentType;
    private SecrecyLevel documentSecrecyLevel;
    private Set<DocumentHistory> changes;
    private Set<DocumentVersion> versions;


}
