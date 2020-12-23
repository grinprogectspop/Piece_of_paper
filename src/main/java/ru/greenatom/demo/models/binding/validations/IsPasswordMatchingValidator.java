package ru.greenatom.demo.models.binding.validations;


import ru.greenatom.demo.models.binding.DocumentBuildingCreateModel;
import ru.greenatom.demo.models.binding.DocumentBuildingSaveModel;
import ru.greenatom.demo.models.binding.UserBuildingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordMatchingValidator implements ConstraintValidator<IsPasswordMatching, Object> {
    @Override
    public void initialize(IsPasswordMatching isPasswordMatching) {

    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (object instanceof UserBuildingModel) {
                UserBuildingModel userBuildingModel = (UserBuildingModel) object;
                return userBuildingModel.getPassword().equals(userBuildingModel.getConfirmPassword());
            }
            if (object instanceof DocumentBuildingSaveModel) {
                DocumentBuildingSaveModel documentBuildingSaveModel = (DocumentBuildingSaveModel) object;
                return documentBuildingSaveModel.getPassword().equals(documentBuildingSaveModel.getConfirmPassword());
            }
            if (object instanceof DocumentBuildingCreateModel) {
                DocumentBuildingCreateModel documentBuildingCreateModel = (DocumentBuildingCreateModel) object;
                return documentBuildingCreateModel.getPassword()
                                                  .equals(documentBuildingCreateModel.getConfirmPassword());
            }
        } catch (NullPointerException npe) {
            return false;
        }


        return false;
    }
}
