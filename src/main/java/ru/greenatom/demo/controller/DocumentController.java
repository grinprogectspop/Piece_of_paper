package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.domain.*;
import ru.greenatom.demo.domain.dto.ChangeAccessDto;
import ru.greenatom.demo.domain.dto.CreatedDocumentDto;
import ru.greenatom.demo.domain.dto.SavedDocumentDto;
import ru.greenatom.demo.repo.*;
import ru.greenatom.demo.service.DocumentService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 **/
@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final ModelMapper modelMapper;

    private final DocumentRepo documentRepo;
    private final DocumentService documentService;
    private final SecrecyLevelRepo secrecyLevelRepo;
    private final DocumentVersionRepo documentVersionRepo;
    private final DocumentHistoryRepo documentHistoryRepo;
    private final DocumentTypeRepo documentTypeRepo;
    private final UserRepo userRepo;
    private final DocumentAccessRepo documentAccessRepo;

    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    public DocumentController(ModelMapper modelMapper, DocumentRepo documentRepo,
                              DocumentService documentService,
                              SecrecyLevelRepo secrecyLevelRepo,
                              DocumentVersionRepo documentVersionRepo,
                              DocumentHistoryRepo documentHistoryRepo, DocumentTypeRepo documentTypeRepo, UserRepo userRepo, DocumentAccessRepo documentAccessRepo) {
        this.modelMapper = modelMapper;
        this.documentRepo = documentRepo;
        this.documentService = documentService;
        this.secrecyLevelRepo = secrecyLevelRepo;
        this.documentVersionRepo = documentVersionRepo;
        this.documentHistoryRepo = documentHistoryRepo;
        this.documentTypeRepo = documentTypeRepo;
        this.userRepo = userRepo;
        this.documentAccessRepo = documentAccessRepo;
    }

    /**
     * @param createdDocumentDto входной класс (Тело запроса)
     * @param bindingResult      проверяет documentBuildingCreateModel на корректность(хранит в себе ошибки при сборке и собран ли он )
     **/
    @PostMapping
    @ResponseBody
    public Map<String, Object> create(
            @RequestBody @Valid CreatedDocumentDto createdDocumentDto,
            BindingResult bindingResult, Authentication user
    ) {
        Map<String, Object> strings = new HashMap<>();
        logger.info("documentBuildingCreateModel собрана:" + !bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            bindingResult.getAllErrors().forEach(e -> {
                logger.error("Error сборки documentBuildingCreateModel:" + e.getDefaultMessage());
                errors.add(e.getDefaultMessage());
            });
            strings.put("error", errors);
        } else {
            strings.put("id", this.documentService.create(createdDocumentDto, (User) user.getPrincipal()).getDocumentId());
        }
        return strings;
    }

    @Deprecated
    @GetMapping("/test")
    @ResponseBody
    @JsonView(Views.documents.class)
    public Document getDocumentId() {
        Document document = new Document();
        document.setCreationDate(LocalDateTime.now());
        document.setDocumentName("name");
        document.setPassword("name");

        //   document.setAccessTypes(     Collections.singleton(Action.SAVE));

        SecrecyLevel secrecyLevel = new SecrecyLevel();
        secrecyLevel.setSecrecyName("SecrecyName");
        secrecyLevel.setDocuments(Collections.singleton(document));

        DocumentType documentType = new DocumentType();
        documentType.setDocumentTypeName("DocumentTypeName");
        documentType.setDocuments(Collections.singleton(document));

        document.setDocumentSecrecyLevel(secrecyLevel);
        document.setDocumentType(documentType);

        this.secrecyLevelRepo.save(secrecyLevel);
        this.documentTypeRepo.save(documentType);
        this.documentRepo.save(document);

        return document;
    }

    @DeleteMapping("/{documentId}")
    @ResponseBody
    public long getDocumentDelete(@PathVariable String documentId) {
        return documentService.delete(Long.parseLong(documentId)).getDocumentId();
    }

    /**
     * @param buildingSaveModel входной класс (Тело запроса)
     * @param bindingResult     проверяет documentBuildingCreateModel на корректность(хранит в себе
     *                          ошибки при сборке и собран ли он )
     **/
    @PutMapping("/{documentId}")
    @ResponseBody
    public Map<String, Object> save(
            @PathVariable String documentId,
            @RequestBody @Valid SavedDocumentDto buildingSaveModel,
            BindingResult bindingResult
    ) {
        Map<String, Object> strings = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();

            bindingResult.getAllErrors().forEach(e -> {
                logger.error("Error сборки DocumentBuildingSaveModel:" + e.getDefaultMessage());
                errors.add(e.getDefaultMessage());
            });

            strings.put("error", errors);
        } else {
            Document document = this.documentService.save(buildingSaveModel);
            logger.info("DocumentBuildingSaveModel собрана:" + !bindingResult.hasErrors());
            strings.put("id", document.getDocumentId());
            strings.put("versionDocument", document.getDocumentId());
        }

        return strings;
    }

    @GetMapping("/{documentId}/{versionName}")
    public DocumentVersion getDocumentByVersion(
            @PathVariable Long documentId,
            @PathVariable String versionName
    ) {
        Document doc = documentRepo.findByDocumentId(documentId);
        return documentVersionRepo.findByVersionNameAndDocument(versionName, doc);
    }

    /**
     * @param idDocument      document id
     * @param userAuth        which giving access to the document
     * @param changeAccessDto dto access
     */
    @PutMapping("/d/{idDocument}")
    @ResponseBody
    public void changeAccess(
            @PathVariable Long idDocument,
            Authentication userAuth,
            @RequestBody ChangeAccessDto changeAccessDto
    ) {
        User user = (User) userAuth.getPrincipal();
        if (documentHistoryRepo.findByActions(Action.CREATE)
                .get(0).getAuthor().equals(user)) {

            User userToChange = userRepo.findByUserId(changeAccessDto.getUserToChangeId());
            List<Action> actionList = documentAccessRepo.findActionByUserUserIdAndDocumentAccessId(userToChange.getUserId(),
                    changeAccessDto.getDocumentId());

            if (!actionList.contains(changeAccessDto.getAction())) {
                DocumentAccess documentAccess = new DocumentAccess();
                documentAccess.setDocument(documentRepo.findByDocumentId(idDocument));
                documentAccess.setUser(userRepo.findByUserId(changeAccessDto.getUserToChangeId()));
                documentAccess.setAccessTypes(Collections.singleton(changeAccessDto.getAction()));
                documentAccessRepo.save(documentAccess);
            }
        }
    }
}
