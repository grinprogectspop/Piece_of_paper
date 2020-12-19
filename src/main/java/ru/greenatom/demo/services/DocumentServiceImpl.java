package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.*;
import ru.greenatom.demo.models.binding.DocumentBuildingCreateModel;
import ru.greenatom.demo.models.binding.DocumentBuildingSaveModel;
import ru.greenatom.demo.repo.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final ModelMapper modelMapper;
    private final DocumentRepo documentRepo;
    private final SecrecyLevelRepo secrecyLevelRepo;
    private final DocumentTypeRepo documentTypeRepo;
    private final DocumentVersionRepo documentVersionRepo;
    private final DocumentHistoryRepo documentHistoryRepo;
    private final UserRepo userRepo;

    public DocumentServiceImpl(ModelMapper modelMapper, DocumentRepo documentRepo,
                               SecrecyLevelRepo secrecyLevelRepo,
                               DocumentTypeRepo documentTypeRepo,
                               DocumentVersionRepo documentVersionRepo,
                               DocumentHistoryRepo documentHistoryRepo, UserRepo userRepo) {
        this.modelMapper = modelMapper;
        this.documentRepo = documentRepo;
        this.secrecyLevelRepo = secrecyLevelRepo;
        this.documentTypeRepo = documentTypeRepo;
        this.documentVersionRepo = documentVersionRepo;
        this.documentHistoryRepo = documentHistoryRepo;
        this.userRepo = userRepo;
    }

    @Override public Document crate(DocumentBuildingCreateModel documentBuildingCreateModel) {
        Document document = new Document();
        document.setDocumentName("setDocumentName");
        document.setCreationDate(LocalDateTime.now());
        document.setPassword(documentBuildingCreateModel.getPassword());

        //SecrecyLevel
        SecrecyLevel secrecyLevel = new SecrecyLevel();
        secrecyLevel.setSecrecyName("setSecrecyName");
        secrecyLevel.setDocuments(Collections.singleton(document));
        document.setDocumentSecrecyLevel(secrecyLevel);

        //DocumentType
        DocumentType documentType = new DocumentType();
        documentType.setDocumentTypeName("setDocumentTypeName");
        documentType.setDocuments(Collections.singleton(document));
        document.setDocumentType(documentType);

        //History
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setDocument(document);
        documentHistory.setActions(Set.of(Action.READ, Action.SAVE));
        documentHistory.setActionDate(LocalDateTime.now());
        documentHistory.setAuthor(userRepo.findOneByUserId(documentBuildingCreateModel.getUserId()));
        documentHistory.setDescription("setDescription");
        document.setChanges(Collections.singleton(documentHistory));

        //Version
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setDocument(document);
        documentVersion.setUrl("URL1");
        documentVersion.setDate(LocalDateTime.now());
        documentVersion.setVersionName("0.0.0");
        document.setVersions(Collections.singleton(documentVersion));

        //Version and History
        documentVersion.setDocumentChanges(Collections.singleton(documentHistory));
        documentHistory.setDocumentVersion(documentVersion);

        //Save
        documentRepo.save(document);
        secrecyLevelRepo.save(secrecyLevel);
        documentTypeRepo.save(documentType);
        documentHistoryRepo.save(documentHistory);
        documentVersionRepo.save(documentVersion);





        return document;
    }

    @Override public Document save(DocumentBuildingSaveModel documentBuildingSaveModel) {
        Document documentNew = new Document();

        return documentNew;
    }

    @Override public Document delete(long documentId) {
        Document document = documentRepo.findByDocumentId(documentId);
        document.setDeleted(true);
        documentRepo.save(document);
        return document;
    }
}
