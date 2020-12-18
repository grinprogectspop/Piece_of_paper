package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
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

//  @ElementCollection(targetClass = Action.class, fetch = FetchType.EAGER)
//  @CollectionTable(name = "access", joinColumns = @JoinColumn(name = "document_id"))
//  @Enumerated(EnumType.STRING)
//  private Set<Action> accessTypes;

  @ManyToOne
  @JoinColumn(name = "document_type_id")
  private DocumentType documentType;

  @ManyToOne
  @JoinColumn(name = "secrecy_level_id")
  @JsonView(Views.documents.class)
  private SecrecyLevel documentSecrecyLevel;

  @OneToMany(mappedBy = "document",
          orphanRemoval = true,
          cascade = CascadeType.ALL
  )
  @JsonView(Views.documents.class)
  private Set<DocumentHistory> changes;

  @OneToMany(mappedBy = "document",
          orphanRemoval = true,
          cascade = CascadeType.ALL
  )
  @JsonView(Views.documents.class)
  private Set<DocumentVersion> versions;
}