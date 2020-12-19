package ru.greenatom.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity

public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentTypeId;

    @NotBlank

    private String documentTypeName;

    @OneToMany(mappedBy = "documentType", cascade = CascadeType.ALL)
    private Set<Document> documents;

    public Long getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Long documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
