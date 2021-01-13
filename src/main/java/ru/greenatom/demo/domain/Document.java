package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@ToString(of = { "documentId", "documentName" })
@EqualsAndHashCode(of = { "documentId" })
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonView(Views.documents.class)
  private Long documentId;

  @NotBlank
  @JsonView(Views.documents.class)
  private String documentName;

  @Column(updatable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonView(Views.documents.class)
  private LocalDateTime creationDate;

  @NotBlank
  private String password;

  @ColumnDefault(value = "false")
  @JsonView(Views.documents.class)
  private boolean deleted;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "document_type_id")
  private DocumentType documentType;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "secrecy_level_id")
  @JsonView(Views.documents.class)
  private SecrecyLevel documentSecrecyLevel;

  @OneToMany(mappedBy = "document",
             orphanRemoval = true,
             fetch = FetchType.EAGER,
             cascade = CascadeType.ALL
  )
  @JsonView(Views.documents.class)
  private Set<DocumentHistory> changes;

  @OneToMany(mappedBy = "document",
          orphanRemoval = true,
          fetch = FetchType.EAGER,
          cascade = CascadeType.ALL
  )
  private Set<DocumentAccess> documentAccesses;

  @OneToMany(mappedBy = "document",
             orphanRemoval = true,
             fetch = FetchType.EAGER,
             cascade = CascadeType.ALL
  )
  @JsonView(Views.documents.class)
  private Set<DocumentVersion> versions;


  public Set<DocumentAccess> getDocumentAccesses() {
    return documentAccesses;
  }

  public void setDocumentAccesses(Set<DocumentAccess> documentAccesses) {
    this.documentAccesses = documentAccesses;
  }

  public Long getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Long documentId) {
    this.documentId = documentId;
  }

  public String getDocumentName() {
    return documentName;
  }

  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  public DocumentType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
  }

  public SecrecyLevel getDocumentSecrecyLevel() {
    return documentSecrecyLevel;
  }

  public void setDocumentSecrecyLevel(SecrecyLevel documentSecrecyLevel) {
    this.documentSecrecyLevel = documentSecrecyLevel;
  }

  public Set<DocumentHistory> getChanges() {
    return changes;
  }

  public void setChanges(Set<DocumentHistory> changes) {
    this.changes = changes;
  }

  public Set<DocumentVersion> getVersions() {
    return versions;
  }

  public void setVersions(Set<DocumentVersion> versions) {
    this.versions = versions;
  }
}