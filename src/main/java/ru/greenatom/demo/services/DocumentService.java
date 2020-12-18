package ru.greenatom.demo.services;

import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.models.binding.DocumentBuildingCreateModel;
import ru.greenatom.demo.models.binding.DocumentBuildingSaveModel;

public interface DocumentService {
    Document crate(DocumentBuildingCreateModel documentBuildingCreateModel);
    Document save(DocumentBuildingSaveModel documentBuildingSaveModel);
    Document delete(long documentId);
}
