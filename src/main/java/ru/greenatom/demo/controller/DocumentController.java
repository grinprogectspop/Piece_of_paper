package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentType;
import ru.greenatom.demo.domain.SecrecyLevel;
import ru.greenatom.demo.domain.Views;
import ru.greenatom.demo.models.binding.DocumentBuildingCreateModel;
import ru.greenatom.demo.models.binding.DocumentBuildingSaveModel;
import ru.greenatom.demo.repo.*;
import ru.greenatom.demo.services.DocumentService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

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


    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Autowired
    public DocumentController(ModelMapper modelMapper, DocumentRepo documentRepo,
                              DocumentService documentService,
                              SecrecyLevelRepo secrecyLevelRepo,
                              DocumentVersionRepo documentVersionRepo,
                              DocumentHistoryRepo documentHistoryRepo, DocumentTypeRepo documentTypeRepo) {
        this.modelMapper = modelMapper;
        this.documentRepo = documentRepo;
        this.documentService = documentService;
        this.secrecyLevelRepo = secrecyLevelRepo;
        this.documentVersionRepo = documentVersionRepo;
        this.documentHistoryRepo = documentHistoryRepo;
        this.documentTypeRepo = documentTypeRepo;
    }

    /**
     * @param documentBuildingCreateModel входной класс (Тело запроса)
     * @param bindingResult проверяет documentBuildingCreateModel на корректность(хранит в себе ошибки при сборке и собран ли он )
     **/
    @PostMapping
    @ResponseBody
    public Map<String, Object> crate(@RequestBody @Valid DocumentBuildingCreateModel documentBuildingCreateModel,
                                     BindingResult bindingResult) {
        Map<String, Object> strings = new HashMap<>();
        logger.info("documentBuildingCreateModel собрана:" + !bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            bindingResult.getAllErrors().forEach(e -> {
                logger.error("Error сборки documentBuildingCreateModel:" + e.getDefaultMessage());
                errors.add(e.getDefaultMessage());
            });
            strings.put("error", errors);
            return strings;
        } else {

            strings.put("id", this.documentService.crate(documentBuildingCreateModel).getDocumentId());
            return strings;
        }

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

    @PutMapping("/delete/{idDocument}")
    @ResponseBody
    public Document delete(@PathVariable String idDocument) {
        return documentService.delete(Long.parseLong(idDocument));
    }
    /**
     * @param buildingSaveModel входной класс (Тело запроса)
     * @param bindingResult проверяет documentBuildingCreateModel на корректность(хранит в себе
     *                      ошибки при сборке и собран ли он )
     **/
    @PutMapping("/{idDocument}")
    @ResponseBody
    public Map<String, Object> save(@PathVariable String idDocument,
                                    @RequestBody @Valid DocumentBuildingSaveModel buildingSaveModel,
                                    BindingResult bindingResult) {
        Map<String, Object> strings = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();

            bindingResult.getAllErrors().forEach(e -> {
                logger.error("Error сборки DocumentBuildingSaveModel:" + e.getDefaultMessage());
                errors.add(e.getDefaultMessage());
            });
            strings.put("error", errors);
            return strings;
        } else {
            Document document =this.documentService.save(buildingSaveModel);
            logger.info("DocumentBuildingSaveModel собрана:" + !bindingResult.hasErrors());
            strings.put("id", document.getDocumentId());
            strings.put("versionDocument",document.getDocumentId());
            return strings;
        }
    }

}

