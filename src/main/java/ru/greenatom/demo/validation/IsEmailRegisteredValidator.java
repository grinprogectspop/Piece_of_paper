package ru.greenatom.demo.validation;



import org.springframework.beans.factory.annotation.Autowired;
import ru.greenatom.demo.repo.UserRepo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailRegisteredValidator implements ConstraintValidator<IsEmailRegistered,String> {
    @Autowired
    private UserRepo userRepo;

    @Override
    public void initialize(IsEmailRegistered isEmailRegister) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return this.userRepo.findByEmail(s) == null;
    }
}
