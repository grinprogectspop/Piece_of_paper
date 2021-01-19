package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Catalog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long catalogId;

  @NotBlank
  private String catalogName;

  @NotBlank
  private String description;

  @ManyToMany
  @JoinTable(
    name = "document_catalog",
    joinColumns = @JoinColumn(name = "catalog_id"),
    inverseJoinColumns = @JoinColumn(name = "document_id")
  )
  private Set<Document> documents;

  public Set<Document> getDocuments() {
    return documents;
  }

  public void setDocuments(Set<Document> documents) {
    this.documents = documents;
  }

  public Long getCatalogId() {
    return catalogId;
  }

  public void setCatalogId(Long catalogId) {
    this.catalogId = catalogId;
  }

  public String getCatalogName() {
    return catalogName;
  }

  public void setCatalogName(String catalogName) {
    this.catalogName = catalogName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
