package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.Document;

import java.util.Set;


@Data
public class SecrecyLevelModel {
    private Long secrecyId;
    private String secrecyName;
    private Set<Document> documents;
}
