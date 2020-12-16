package ru.greenatom.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServicesImpl implements UserServices {
    private final ModelMapper modelMapper;

    @Autowired
    public UserServicesImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public void crateUser() {

    }

    @Override
    public void editUser() {

    }

    @Override
    public void deleteUser() {

    }
}
