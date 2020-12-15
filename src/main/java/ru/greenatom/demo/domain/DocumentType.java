package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long documentTypeId;

    @NotBlank
    private String documentTypeName;

    @OneToMany(mappedBy = "documentType")
    private Set<Document> documents;
}
