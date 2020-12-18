package ru.greenatom.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
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
