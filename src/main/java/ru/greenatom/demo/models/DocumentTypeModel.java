package ru.greenatom.demo.models;

import lombok.Data;
import ru.greenatom.demo.domain.Document;

import java.util.Set;


@Data
public class DocumentTypeModel {

    private Long documentTypeId;
    private String documentTypeName;
    private Set<Document> documents;
}
