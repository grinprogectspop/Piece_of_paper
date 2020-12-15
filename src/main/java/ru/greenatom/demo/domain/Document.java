package ru.greenatom.demo.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Document {
  @Id
  private Long documentId;

  @NotBlank
  private String documentName;

  @NotBlank
  private LocalDateTime creationDate;

  @NotBlank
  private String password;

  @NotBlank
  @ColumnDefault(value = "false")
  private boolean deleted;

  @ElementCollection(targetClass = AccessType.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "access", joinColumns = @JoinColumn(name = "document_id"))
  @Enumerated(EnumType.STRING)
  private Set<AccessType> accessTypes;
}
