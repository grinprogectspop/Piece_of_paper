package ru.greenatom.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.greenatom.demo.domain.Action;
import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.DocumentAccess;
import ru.greenatom.demo.domain.DocumentType;
import ru.greenatom.demo.domain.SecrecyLevel;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.domain.Views;
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
    @ApiOperation(value = "Создание документа", response = Long.class, responseContainer = "id", httpMethod = "POST",
                  notes = "API для создания документа")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "x-auth-token", value = "", dataType = "string",
                                                 required = true, paramType = "header")})

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = CreatedDocumentDto.class,
                         responseContainer = "Данные документа"),})
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
    @ApiOperation(value = "Удаление документа", response = Long.class, responseContainer = "id", httpMethod = "GET",
                  notes = "API для удаления документа")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "x-auth-token", value = "", dataType = "string",
                                                 required = true, paramType = "header")})

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = String.class,
                         responseContainer = "Удаление документа"),})

    @GetMapping("/{documentId}")
    @ResponseBody
    public long getDocumentDelete(@PathVariable String documentId) {
        return documentService.delete(Long.parseLong(documentId)).getDocumentId();
    }

    /**
     * @param buildingSaveModel входной класс (Тело запроса)
     * @param bindingResult     проверяет documentBuildingCreateModel на корректность(хранит в себе
     *                          ошибки при сборке и собран ли он )
     **/
    @ApiOperation(value = "Сохранение документа", response = Long.class, responseContainer = "???", httpMethod = "GET",
                  notes = "API для сохранения документа", responseReference = "123")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "x-auth-token", value = "", dataType = "string",
                                                 required = true, paramType = "header")})

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Map.class,
                         responseContainer = "Сохранение документа"),})
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

    /**
     * @param idDocument      document id
     * @param userAuth        which giving access to the document
     * @param changeAccessDto dto access
     */
    @ApiOperation(value = "Изменение доступа к документу", response = Long.class, responseContainer = "???", httpMethod = "GET",
                  notes = "API для изменение доступа к документу")
    @ApiImplicitParams({
                               @ApiImplicitParam(name = "x-auth-token", value = "", dataType = "string",
                                                 required = true, paramType = "header")})

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = void.class,
                         responseContainer = "Изменение доступа к документу")})
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