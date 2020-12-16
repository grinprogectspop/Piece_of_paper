package ru.greenatom.demo.services;

public interface DocumentServices {
    void crateDocument();

    void editDocument(long id);

    void deleteDocument(long id);
}
