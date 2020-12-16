package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DocumentServicesImpl implements DocumentServices {
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServicesImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void crateDocument() {

    }

    @Override
    public void editDocument(long id) {

    }

    @Override
    public void deleteDocument(long id) {

    }


}
