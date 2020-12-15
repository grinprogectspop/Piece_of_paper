package ru.greenatom.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class SecrecyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long secrecyId;

    @NotBlank
    private String secrecyName;

    @OneToMany(mappedBy = "documentSecrecyLevel")
    private Set<Document> documents;
}
