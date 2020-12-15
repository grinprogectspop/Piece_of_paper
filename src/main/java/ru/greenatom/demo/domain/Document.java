package ru.greenatom.demo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@ToString(of = { "documentId", "documentName" })
@EqualsAndHashCode(of = { "documentId" })
public class Document {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @ElementCollection(targetClass = Action.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "access", joinColumns = @JoinColumn(name = "document_id"))
  @Enumerated(EnumType.STRING)
  private Set<Action> accessTypes;

  @ManyToOne
  @JoinColumn(name = "document_type_id")
  private DocumentType documentType;

  @ManyToOne
  @JoinColumn(name = "secrecy_level_id")
  private SecrecyLevel documentSecrecyLevel;

  @OneToMany(mappedBy = "document",
          orphanRemoval = true,
          cascade = CascadeType.ALL
  )
  private Set<DocumentHistory> changes;

  @OneToMany(mappedBy = "document",
          orphanRemoval = true,
          cascade = CascadeType.ALL
  )
  private Set<DocumentVersion> versions;
}