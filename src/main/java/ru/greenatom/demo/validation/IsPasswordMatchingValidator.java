package ru.greenatom.demo.validation;


import ru.greenatom.demo.domain.dto.CreatedDocumentDto;
import ru.greenatom.demo.domain.dto.SavedDocumentDto;
import ru.greenatom.demo.domain.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching, Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (object instanceof UserDto) {
                UserDto userDto = (UserDto) object;
                return userDto
                        .getPassword()
                        .equals(userDto.getConfirmPassword());
            }
            if (object instanceof SavedDocumentDto) {
                SavedDocumentDto savedDocumentDto = (SavedDocumentDto) object;
                return savedDocumentDto
                        .getPassword()
                        .equals(savedDocumentDto.getConfirmPassword());
            }
            if (object instanceof CreatedDocumentDto) {
                CreatedDocumentDto createdDocumentDto = (CreatedDocumentDto) object;
                return createdDocumentDto
                        .getPassword()
                        .equals(createdDocumentDto.getConfirmPassword());
            }
        } catch (NullPointerException npe) {
            return false;
        }

        return false;
    }
}