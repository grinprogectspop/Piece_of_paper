package ru.greenatom.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity

public class SecrecyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long secrecyId;

    @NotBlank
    private String secrecyName;

    @OneToMany(mappedBy = "documentSecrecyLevel")
    private Set<Document> documents;

    public Long getSecrecyId() {
        return secrecyId;
    }

    public void setSecrecyId(Long secrecyId) {
        this.secrecyId = secrecyId;
    }

    public String getSecrecyName() {
        return secrecyName;
    }

    public void setSecrecyName(String secrecyName) {
        this.secrecyName = secrecyName;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
