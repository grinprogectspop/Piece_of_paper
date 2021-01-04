package ru.greenatom.demo.service;

import ru.greenatom.demo.domain.Document;
import ru.greenatom.demo.domain.User;
import ru.greenatom.demo.domain.dto.CreatedDocumentDto;
import ru.greenatom.demo.domain.dto.SavedDocumentDto;

public interface DocumentService {
    Document create(CreatedDocumentDto createdDocumentDto, User principal);
    Document save(SavedDocumentDto savedDocumentDto);
    Document delete(long documentId);
}
