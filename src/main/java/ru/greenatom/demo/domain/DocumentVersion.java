package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentVersionId;

    @NotBlank
    private String versionName;

    @NotBlank
    private LocalDateTime date;

    @NotBlank
    private String url;

    @OneToMany(
            mappedBy = "documentVersion",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    private Set<DocumentHistory> documentChanges;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;
}
