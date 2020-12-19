package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity

public class DocumentVersion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentVersionId;

    @NotBlank
    private String versionName;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    @NotBlank
    private String url;

    @OneToMany(
            mappedBy = "documentVersion",
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )

    private Set<DocumentHistory> documentChanges;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    public Set<DocumentHistory> getDocumentChanges() {
        return documentChanges;
    }

    public void setDocumentChanges(Set<DocumentHistory> documentChanges) {
        this.documentChanges = documentChanges;
    }

    public Long getDocumentVersionId() {
        return documentVersionId;
    }

    public void setDocumentVersionId(Long documentVersionId) {
        this.documentVersionId = documentVersionId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
