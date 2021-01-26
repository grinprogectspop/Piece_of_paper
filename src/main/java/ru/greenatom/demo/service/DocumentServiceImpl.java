package ru.greenatom.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.*;
import ru.greenatom.demo.domain.dto.CreatedDocumentDto;
import ru.greenatom.demo.domain.dto.SavedDocumentDto;
import ru.greenatom.demo.repo.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

/**
 * Сервис для работы с документом
 * **/
@Service
public class DocumentServiceImpl implements DocumentService {
    private final ModelMapper modelMapper;
    private final DocumentRepo documentRepo;
    private final SecrecyLevelRepo secrecyLevelRepo;
    private final DocumentTypeRepo documentTypeRepo;
    private final DocumentHistoryRepo documentHistoryRepo;
    private final UserRepo userRepo;

    private final VersionService versionService;

    public DocumentServiceImpl(
            ModelMapper modelMapper,
            DocumentRepo documentRepo,
            SecrecyLevelRepo secrecyLevelRepo,
            DocumentTypeRepo documentTypeRepo,
            DocumentHistoryRepo documentHistoryRepo,
            UserRepo userRepo,
            VersionService versionService) {
        this.modelMapper = modelMapper;
        this.documentRepo = documentRepo;
        this.secrecyLevelRepo = secrecyLevelRepo;
        this.documentTypeRepo = documentTypeRepo;
        this.documentHistoryRepo = documentHistoryRepo;
        this.userRepo = userRepo;
        this.versionService = versionService;
    }

    @Override
    public Document create(CreatedDocumentDto createdDocumentDto, User principal) {
        Document createdDocument = new Document();
        createdDocument.setDocumentName(createdDocumentDto.getDocumentName());
        createdDocument.setCreationDate(LocalDateTime.now());
        createdDocument.setPassword(createdDocumentDto.getPassword());

        // SecrecyLevel
        SecrecyLevel secrecyLevel = new SecrecyLevel();
        secrecyLevel.setSecrecyName("setSecrecyName");
        secrecyLevel.setDocuments(Collections.singleton(createdDocument));
        createdDocument.setDocumentSecrecyLevel(secrecyLevel);

        // DocumentType
        DocumentType documentType = new DocumentType();
        documentType.setDocumentTypeName("setDocumentTypeName");
        documentType.setDocuments(Collections.singleton(createdDocument));
        createdDocument.setDocumentType(documentType);

        // DocumentHistory
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setDocument(createdDocument);
        documentHistory.setActions(Set.of(Action.READ, Action.SAVE,Action.CREATE));
        documentHistory.setActionDate(LocalDateTime.now());
        documentHistory.setAuthor(principal);
        documentHistory.setDescription("No description");
        createdDocument.setChanges(Collections.singleton(documentHistory));

        // DocumentVersion
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setDocument(createdDocument);
        documentVersion.setUrl(createdDocumentDto.getUrl());
        documentVersion.setDate(LocalDateTime.now());
        documentVersion.setVersionName("0.0.0.1");
        createdDocument.setVersions(Collections.singleton(documentVersion));

        // DocumentVersion & DocumentHistory
        documentVersion.setDocumentChanges(Collections.singleton(documentHistory));
        documentHistory.setDocumentVersion(documentVersion);

        // Saving changes
        documentRepo.save(createdDocument);

        return createdDocument;
    }

    @Override
    public Document save(SavedDocumentDto savedDocumentDto) {
        Document savedDocument = documentRepo.findByDocumentId(savedDocumentDto.getDocumentId());

        // DocumentHistory
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setDocument(savedDocument);
        documentHistory.setActionDate(LocalDateTime.now());
        documentHistory.setActions(Set.of(Action.WRITE, Action.SAVE));
        documentHistory.setDescription(savedDocumentDto.getDescription());
        documentHistory.setAuthor(userRepo.findByUserId(savedDocumentDto.getUserId()));
        documentHistory.setDocumentVersion(savedDocumentDto.getVersionEdit());

        // DocumentVersion
        DocumentVersion version = versionService.saveVersion(
                savedDocumentDto.getVersionEdit(), savedDocument, documentHistory);

        documentHistory.setDocumentVersion(version);

        savedDocument.getChanges().add(documentHistory);
        savedDocument.getVersions().add(version);
        savedDocument.setPassword(savedDocumentDto.getPassword());

        documentRepo.save(savedDocument);

        return savedDocument;
    }

    @Override
    public Document delete(long documentId) {
        Document document = documentRepo.findByDocumentId(documentId);
        document.setDeleted(true);
        documentRepo.save(document);
        return document;
    }
}