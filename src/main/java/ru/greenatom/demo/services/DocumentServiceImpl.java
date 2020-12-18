package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentHistory;
import ru.greenatom.demo.domain.DocumentVersion;
import ru.greenatom.demo.models.binding.DocumentBuildingCreateModel;
import ru.greenatom.demo.models.binding.DocumentBuildingSaveModel;
import ru.greenatom.demo.repo.*;

import java.time.LocalDateTime;
import java.util.Collections;

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
        //создание документа
        Document document = this.modelMapper.map(documentBuildingCreateModel, Document.class);
        document.setCreationDate(LocalDateTime.now());


        //documentSecrecyLevel
        document.getDocumentSecrecyLevel().setDocuments(Collections.singleton(document));

        secrecyLevelRepo.save(document.getDocumentSecrecyLevel());


        //documentType
        document.getDocumentType().setDocuments(Collections.singleton(document));

        documentTypeRepo.save(document.getDocumentType());

        documentRepo.save(document);
        //changes
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionDate(LocalDateTime.now());
        documentHistory.setDocument(document);
        documentHistory.setDescription("setDescription");
        documentHistoryRepo.save(documentHistory);


        document.setChanges(Collections.singleton(documentHistory));


        // DocumentVersion
        DocumentVersion documentVersion = new DocumentVersion();
        documentVersion.setVersionName("0.0.0");
        documentVersion.setDate(LocalDateTime.now());
        //Todo адекватный  URL
        documentVersion.setUrl("URL");
        documentVersion.setDocument(document);


        documentVersionRepo.save(documentVersion);

        Action action = Action.WRITE;

        documentHistory.setActions(Collections.singleton(action));

        documentHistoryRepo.save(documentHistory);


        return document;
    }

    @Override public Document save(DocumentBuildingSaveModel documentBuildingSaveModel) {
        Document documentNew = this.modelMapper.map(documentBuildingSaveModel, Document.class);
        Document documentOld = documentRepo.findByDocumentId(documentBuildingSaveModel.getIdDocument());
        //Todo documentNew and documentNew не получается получить
        documentNew.setChanges(documentOld.getChanges());
        documentNew.setVersions(documentOld.getVersions());

        DocumentVersion documentVersion = documentBuildingSaveModel.getVersionEdit();

        documentVersion.setDate(LocalDateTime.now());
        //Todo адекватный  URL
        documentVersion.setUrl("URL1");

        documentVersionRepo.save(documentBuildingSaveModel.getVersionEdit());
        DocumentHistory documentHistory = new DocumentHistory();
        documentHistory.setActionDate(LocalDateTime.now());
        documentHistory.setDocument(documentNew);
        documentHistory.setDescription("setDescription");

        documentNew.getChanges().add(documentHistory);
        documentHistoryRepo.save(documentHistory);
        documentHistory.setActions(Collections.singleton(Action.SAVE));

        documentHistoryRepo.save(documentHistory);
        documentRepo.save(documentNew);
        documentVersionRepo.save(documentVersion);



        return null;
    }

    @Override public Document delete(long documentId) {
        Document document = documentRepo.findByDocumentId(documentId);
        document.setDeleted(true);
        documentRepo.save(document);
        return document;
    }
}
